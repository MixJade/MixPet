package com.forge.controller;

import com.forge.common.Result;
import com.forge.dto.FosterDto;
import com.forge.dto.FosterPetDto;
import com.forge.shiro.RoleConst;
import com.forge.vo.Page;
import com.forge.entity.Foster;
import com.forge.service.IFosterService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 寄养表 前端控制器
 * </p>
 *
 * @author MixJade
 * @since 2023-01-12
 */
@RestController
@RequestMapping("/foster")
public class FosterController {
    private final IFosterService fosterService;

    @Autowired
    public FosterController(IFosterService fosterService) {
        this.fosterService = fosterService;
    }

    @GetMapping("/page")
    public Page<FosterDto> getPage(int numPage, int pageSize, String fosterName) {
        return fosterService.selectByPage(fosterName, numPage, pageSize);
    }

    /**
     * 用户前台：查看被寄养的宠物
     *
     * @return 被寄养宠物的卡片信息
     */
    @GetMapping("/pet")
    public List<FosterPetDto> fosterPet() {
        return fosterService.fosterPet();
    }

    @PostMapping
    @RequiresRoles(RoleConst.NURSE)
    public Result save(@RequestBody Foster foster) {
        if (foster.getFosterMoney() <= 0) return Result.error("金额必须大于零");
        if (fosterService.haveFoster(foster.getPetId()) > 0) return Result.error("当前宠物已在寄养中");
        return Result.choice("添加", fosterService.save(foster));
    }

    @DeleteMapping("/{id}")
    @RequiresRoles(RoleConst.MANAGER)
    public Result delete(@PathVariable Long id) {
        return Result.choice("删除单个", fosterService.deleteById(id));
    }

    @DeleteMapping("/batch/{ids}")
    @RequiresRoles(RoleConst.MANAGER)
    public Result deleteGroup(@PathVariable long[] ids) {
        return Result.choice("删除多个", fosterService.deleteByIds(ids));
    }

    @PutMapping
    @RequiresRoles(RoleConst.MANAGER)
    public Result update(@RequestBody Foster foster) {
        if (foster.getFosterMoney() <= 0) return Result.error("金额必须大于零");
        return Result.choice("修改", fosterService.updateById(foster));
    }
}
