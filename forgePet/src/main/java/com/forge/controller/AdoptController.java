package com.forge.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.forge.common.Result;
import com.forge.dto.AdoptDto;
import com.forge.shiro.RoleConst;
import com.forge.vo.Page;
import com.forge.entity.Adopt;
import com.forge.entity.Client;
import com.forge.service.IAdoptService;
import com.forge.vo.AdoptVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public List<Adopt> getAll() {
        return adoptService.list();
    }

    @GetMapping("/page")
    public Page<AdoptDto> getPage(int numPage, int pageSize, String adoptName) {
        return adoptService.selectByPage(adoptName, numPage, pageSize);
    }

    /**
     * 用户：确认当前宠物是否有待审核订单
     */
    @GetMapping("/inAdopt")
    public int sureAdopt(Long petId) {
        if (SecurityUtils.getSubject().getPrincipal() instanceof Client client) {
            Long clientId = client.getClientId();
            return adoptService.sureInAdopt(petId, clientId);
        } else return 1;
    }

    /**
     * 管理员添加领养订单
     */
    @PostMapping
    @RequiresRoles(RoleConst.MANAGER)
    public Result save(@RequestBody Adopt adopt) {
        return Result.choice("添加", adoptService.save(adopt));
    }

    /**
     * 用户提交领养申请
     */
    @PostMapping("/adoptPet")
    public Result adoptPet(@RequestBody Adopt adopt) {
        if (SecurityUtils.getSubject().getPrincipal() instanceof Client client) {
            Long id = client.getClientId();
            adopt.setClientId(id);
            return Result.choice("申请", adoptService.save(adopt));
        } else return Result.error("用户暂时未登录");
    }

    @DeleteMapping("/{id}")
    @RequiresRoles(RoleConst.MANAGER)
    public Result delete(@PathVariable Long id) {
        return Result.choice("删除单个", adoptService.deleteById(id));
    }

    @DeleteMapping("/batch/{ids}")
    @RequiresRoles(RoleConst.MANAGER)
    public Result deleteGroup(@PathVariable long[] ids) {
        return Result.choice("删除多个", adoptService.deleteByIds(ids));
    }

    @PutMapping
    @RequiresRoles(RoleConst.MANAGER)
    public Result update(@RequestBody Adopt adopt) {
        return Result.choice("修改", adoptService.updateById(adopt));
    }

    /**
     * 管理员审核订单
     */
    @PutMapping("/adopt")
    @RequiresRoles(RoleConst.MANAGER)
    public Result toAdopt(@RequestBody AdoptVo adoptVo) {
        if (!adoptService.petMaster(adoptVo.adoptId())) return Result.error("名花有主");
        Boolean isAdopt = adoptVo.isAdopt();
        Long adoptId = adoptVo.adoptId();
        if (isAdopt == null) return Result.error("操作有误");
        if (isAdopt) return Result.choice("领养宠物", adoptService.adoptAdopt(adoptId, "它被有缘人领养"));
        UpdateWrapper<Adopt> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("adopt_id", adoptId);
        updateWrapper.set("in_adopt", 0);
        return Result.choice("拒绝", adoptService.update(updateWrapper));
    }
}
