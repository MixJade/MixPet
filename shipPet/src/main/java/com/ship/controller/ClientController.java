package com.ship.controller;

import com.ship.common.PhotoConst;
import com.ship.common.Result;
import com.ship.entity.Client;
import com.ship.security.RoleConst;
import com.ship.service.IClientService;
import com.ship.util.StrUtil;
import com.ship.vo.NameVo;
import com.ship.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
    @Secured(RoleConst.MANAGER)
    public Result save(@RequestBody Client client) {
        if (StrUtil.isWhite(client.getClientName())) return Result.error("用户名为空");
        if (StrUtil.isWhite(client.getClientUsername())) return Result.error("用户账号为空");
        if (StrUtil.isWhite(client.getClientPhoto())) client.setClientPhoto(PhotoConst.CLIENT);
        return Result.choice("添加", clientService.save(client));
    }

    @DeleteMapping("/{id}")
    @Secured(RoleConst.MANAGER)
    public Result delete(@PathVariable Long id) {
        return Result.choice("删除单个", clientService.deleteById(id));
    }

    @DeleteMapping("/batch/{ids}")
    @Secured(RoleConst.MANAGER)
    public Result deleteGroup(@PathVariable long[] ids) {
        return Result.choice("删除多个", clientService.deleteByIds(ids));
    }

    @PutMapping
    @Secured({RoleConst.MANAGER, RoleConst.CLIENT})
    public Result update(@RequestBody Client client) {
        if (StrUtil.isWhite(client.getClientName())) return Result.error("用户名为空");
        if (StrUtil.isWhite(client.getClientUsername())) return Result.error("用户账号为空");
        return Result.choice("修改", clientService.updateById(client));
    }

}
