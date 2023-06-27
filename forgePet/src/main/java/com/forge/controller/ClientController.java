package com.forge.controller;

import com.forge.common.PhotoConst;
import com.forge.common.Result;
import com.forge.shiro.RoleConst;
import com.forge.util.StrUtil;
import com.forge.vo.NameVo;
import com.forge.vo.Page;
import com.forge.entity.Client;
import com.forge.service.IClientService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author MixJade
 * @since 2022-12-22
 */
@RestController
@RequestMapping("/client")
public class ClientController {
    private final IClientService clientService;

    @Autowired
    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * 查询用户的名字与id
     */
    @GetMapping
    public List<NameVo> getName() {
        return clientService.selectName();
    }

    /**
     * 根据id查询单个用户
     */
    @GetMapping("/one")
    public Client getOne(Long id) {
        return clientService.getById(id);
    }

    @GetMapping("/page")
    public Page<Client> getPage(int numPage, int pageSize, String clientName) {
        return clientService.selectByPage(clientName, numPage, pageSize);
    }

    @PostMapping
    @RequiresRoles(RoleConst.MANAGER)
    public Result save(@RequestBody Client client) {
        if (StrUtil.isWhite(client.getClientName())) return Result.error("用户名为空");
        if (StrUtil.isWhite(client.getClientUsername())) return Result.error("用户账号为空");
        if (StrUtil.isWhite(client.getClientPhoto())) client.setClientPhoto(PhotoConst.CLIENT);
        return Result.choice("添加", clientService.save(client));
    }

    @DeleteMapping("/{id}")
    @RequiresRoles(RoleConst.MANAGER)
    public Result delete(@PathVariable Long id) {
        return Result.choice("删除单个", clientService.deleteById(id));
    }

    @DeleteMapping("/batch/{ids}")
    @RequiresRoles(RoleConst.MANAGER)
    public Result deleteGroup(@PathVariable long[] ids) {
        return Result.choice("删除多个", clientService.deleteByIds(ids));
    }

    @PutMapping
    @RequiresRoles(value = {RoleConst.MANAGER, RoleConst.CLIENT}, logical = Logical.OR)
    public Result update(@RequestBody Client client) {
        if (StrUtil.isWhite(client.getClientName())) return Result.error("用户名为空");
        return Result.choice("修改", clientService.updateById(client));
    }

}
