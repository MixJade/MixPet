package com.ship.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ship.common.PhotoEnum;
import com.ship.common.Result;
import com.ship.model.entity.Client;
import com.ship.model.vo.NameVo;
import com.ship.security.RoleConst;
import com.ship.service.IClientService;
import com.ship.util.StrUtil;
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
    public Client getOne(Integer id) {
        return clientService.getById(id);
    }

    @GetMapping("/page")
    public IPage<Client> getPage(int numPage, int pageSize, String clientName) {
        return clientService.selectByPage(clientName, numPage, pageSize);
    }

    @PostMapping
    @Secured(RoleConst.MANAGER)
    public Result save(@RequestBody Client client) {
        if (StrUtil.isWhite(client.getClientName())) return Result.error("用户名为空");
        if (StrUtil.isWhite(client.getClientUsername())) return Result.error("用户账号为空");
        if (StrUtil.isWhite(client.getClientPhoto())) client.setClientPhoto(PhotoEnum.CLIENT.getPhotoName());
        if (StrUtil.isWhite(client.getClientPassword())) client.setClientPassword("123456");
        client.setClientPassword(StrUtil.tranPwd(client.getClientPassword()));
        return Result.choice("添加", clientService.save(client));
    }

    @DeleteMapping("/{id}")
    @Secured(RoleConst.MANAGER)
    public Result delete(@PathVariable Integer id) {
        return Result.choice("删除单个", clientService.deleteById(id));
    }

    @DeleteMapping("/batch/{ids}")
    @Secured(RoleConst.MANAGER)
    public Result deleteGroup(@PathVariable List<Integer> ids) {
        return Result.choice("删除多个", clientService.deleteByIds(ids));
    }

    @PutMapping
    @Secured({RoleConst.MANAGER, RoleConst.CLIENT})
    public Result update(@RequestBody Client client) {
        if (StrUtil.isWhite(client.getClientName())) return Result.error("用户名为空");
        return Result.choice("修改", clientService.updateById(client));
    }

}
