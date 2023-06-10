package com.ship.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ship.common.PhotoConst;
import com.ship.common.Result;
import com.ship.dto.PetDto;
import com.ship.entity.Client;
import com.ship.entity.Pet;
import com.ship.security.RoleConst;
import com.ship.service.IPetService;
import com.ship.util.StrUtil;
import com.ship.util.UserUtil;
import com.ship.vo.NameVo;
import com.ship.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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

    /**
     * 添加寄养、挂号单时，查询主人对应的宠物
     */
    @GetMapping("/client")
    public List<NameVo> getByClient(long clientId) {
        return petService.selectByClient(clientId);
    }

    /**
     * 用户个人中心：查询名下宠物
     *
     * @return 宠物列表
     */
    @GetMapping("/clientOne")
    public List<Pet> getByClientOne() {
        if (UserUtil.getUser() instanceof Client client) {
            var queryWrapper = new LambdaQueryWrapper<Pet>();
            queryWrapper.eq(Pet::getClientId, client.getClientId());
            return petService.list(queryWrapper);
        } else return null;
    }

    /**
     * 管理员：添加领养订单时，查询无主宠物
     *
     * @return 无主宠物的姓名与ID
     */
    @GetMapping("/noClient")
    public List<NameVo> getNoClient() {
        return petService.selectNoClient();
    }

    @GetMapping("/page")
    public Page<PetDto> getPage(int numPage, int pageSize, String petName, String clientName) {
        return petService.selectByPage(petName, clientName, numPage, pageSize);
    }

    /**
     * 用户前台查询待领养宠物
     *
     * @return 待领养宠物列表
     */
    @GetMapping("/four")
    public Page<Pet> getFour(int numPage, int pageSize) {
        return petService.selectFour(numPage, pageSize);
    }

    /**
     * 用户查看待领养宠物的详情
     *
     * @param petId 待领养宠物的ID
     * @return 宠物信息
     */
    @GetMapping("/one")
    public Pet getOne(Long petId) {
        return petService.getById(petId);
    }

    @PostMapping
    @Secured(RoleConst.NURSE)
    public Result save(@RequestBody Pet pet) {
        if (StrUtil.isWhite(pet.getPetName())) return Result.error("姓名不能为空");
        if (StrUtil.isWhite(pet.getPetVariety())) return Result.error("品种不能为空");
        if (StrUtil.isWhite(pet.getPetPhoto())) pet.setPetPhoto(PhotoConst.PET);
        return Result.choice("添加", petService.save(pet));
    }

    @DeleteMapping("/{id}")
    @Secured(RoleConst.MANAGER)
    public Result delete(@PathVariable Long id) {
        return Result.choice("删除单个", petService.deleteById(id));
    }

    @DeleteMapping("/batch/{ids}")
    @Secured(RoleConst.MANAGER)
    public Result deleteGroup(@PathVariable long[] ids) {
        return Result.choice("删除多个", petService.deleteByIds(ids));
    }

    @PutMapping
    @Secured(RoleConst.MANAGER)
    public Result update(@RequestBody Pet pet) {
        if (StrUtil.isWhite(pet.getPetName())) return Result.error("姓名不能为空");
        if (StrUtil.isWhite(pet.getPetVariety())) return Result.error("品种不能为空");
        return Result.choice("修改", petService.updatePet(pet));
    }
}
