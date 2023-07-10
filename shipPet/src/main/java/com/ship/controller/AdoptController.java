package com.ship.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ship.common.Result;
import com.ship.dto.AdoptDto;
import com.ship.entity.Adopt;
import com.ship.entity.Client;
import com.ship.security.RoleConst;
import com.ship.service.IAdoptService;
import com.ship.util.UserUtil;
import com.ship.vo.AdoptVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 领养宠物订单 前端控制器
 * </p>
 *
 * @author MixJade
 * @since 2023-01-12
 */
@RestController
@RequestMapping("/adopt")
public class AdoptController {
    private final IAdoptService adoptService;

    @Autowired
    public AdoptController(IAdoptService adoptService) {
        this.adoptService = adoptService;
    }

    @GetMapping("/page")
    public IPage<AdoptDto> getPage(int numPage, int pageSize, String adoptName) {
        return adoptService.selectByPage(adoptName, numPage, pageSize);
    }

    /**
     * 用户：确认当前宠物是否有待审核订单
     */
    @GetMapping("/inAdopt")
    public int sureAdopt(Long petId) {
        if (UserUtil.getUser() instanceof Client client) {
            Long clientId = client.getClientId();
            return adoptService.sureInAdopt(petId, clientId);
        } else return 1;
    }

    /**
     * 管理员添加领养订单
     */
    @PostMapping
    @Secured(RoleConst.MANAGER)
    public Result save(@RequestBody Adopt adopt) {
        if (adopt.getAdoptMoney() == null || adopt.getAdoptMoney() <= 0)
            return Result.error("金额必须大于零");
        return Result.choice("添加", adoptService.save(adopt));
    }

    /**
     * 用户提交领养申请
     */
    @PostMapping("/adoptPet")
    public Result adoptPet(@RequestBody Adopt adopt) {
        if (adopt.getAdoptMoney() <= 0) return Result.error("金额必须大于零");
        if (UserUtil.getUser() instanceof Client client) {
            Long id = client.getClientId();
            adopt.setClientId(id);
            return Result.choice("申请", adoptService.save(adopt));
        } else return Result.error("用户暂时未登录");
    }

    @DeleteMapping("/{id}")
    @Secured(RoleConst.MANAGER)
    public Result delete(@PathVariable Long id) {
        return Result.choice("删除单个", adoptService.deleteById(id));
    }

    @DeleteMapping("/batch/{ids}")
    @Secured(RoleConst.MANAGER)
    public Result deleteGroup(@PathVariable List<Long> ids) {
        return Result.choice("删除多个", adoptService.deleteByIds(ids));
    }

    @PutMapping
    @Secured(RoleConst.MANAGER)
    public Result update(@RequestBody Adopt adopt) {
        if (adopt.getAdoptMoney() <= 0) return Result.error("金额必须大于零");
        return Result.choice("修改", adoptService.updateById(adopt));
    }

    /**
     * 管理员审核订单
     */
    @PutMapping("/adopt")
    @Secured(RoleConst.MANAGER)
    public Result toAdopt(@RequestBody AdoptVo adoptVo) {
        if (!adoptService.petMaster(adoptVo.adoptId())) return Result.error("名花有主");
        Boolean isAdopt = adoptVo.isAdopt();
        Long adoptId = adoptVo.adoptId();
        if (isAdopt == null) return Result.error("操作有误");
        if (isAdopt) return Result.choice("领养宠物", adoptService.adoptAdopt(adoptId, "它有了温暖的家，感恩"));
        UpdateWrapper<Adopt> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("adopt_id", adoptId);
        updateWrapper.set("in_adopt", 0);
        return Result.choice("拒绝", adoptService.update(updateWrapper));
    }
}
