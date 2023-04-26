package com.forge.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.forge.common.Result;
import com.forge.dto.PetDto;
import com.forge.entity.Pet;
import com.forge.service.IPetService;
import com.forge.shiro.RoleConst;
import com.forge.vo.NameVo;
import com.forge.vo.Page;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 宠物信息表，外键用户表 前端控制器
 * </p>
 *
 * @author MixJade
 * @since 2022-12-22
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    private final IPetService petService;

    @Autowired
    public PetController(IPetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public List<NameVo> getAll() {
        return petService.selectName();
    }

    @GetMapping("/client")
    public List<NameVo> getByClient(long clientId) {
        return petService.selectByClient(clientId);
    }

    @GetMapping("/clientOne")
    public List<Pet> getByClientOne(long clientId) {
        var queryWrapper = new LambdaQueryWrapper<Pet>();
        queryWrapper.eq(Pet::getClientId,clientId);
        return petService.list(queryWrapper);
    }

    @GetMapping("/noClient")
    public List<NameVo> getNoClient() {
        return petService.selectNoClient();
    }

    @GetMapping("/page")
    public Page<PetDto> getPage(int numPage, int pageSize, String petName, String clientName) {
        return petService.selectByPage(petName, clientName, numPage, pageSize);
    }

    @GetMapping("/four")
    public Page<Pet> getFour(int numPage, int pageSize) {
        return petService.selectFour(numPage, pageSize);
    }

    @GetMapping("/one")
    public Pet getOne(Long petId) {
        return petService.getById(petId);
    }

    @PostMapping
    @RequiresRoles(RoleConst.NURSE)
    public Result save(@RequestBody Pet pet) {
        return Result.choice("添加", petService.save(pet));
    }

    @DeleteMapping("/{id}")
    @RequiresRoles(RoleConst.MANAGER)
    public Result delete(@PathVariable Long id) {
        return Result.choice("删除单个", petService.deleteById(id));
    }

    @DeleteMapping("/batch/{ids}")
    @RequiresRoles(RoleConst.MANAGER)
    public Result deleteGroup(@PathVariable long[] ids) {
        return Result.choice("删除多个", petService.deleteByIds(ids));
    }

    @PutMapping
    @RequiresRoles(RoleConst.MANAGER)
    public Result update(@RequestBody Pet pet) {
        return Result.choice("修改", petService.updatePet(pet));
    }
}
