package com.ship.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ship.common.Result;
import com.ship.model.entity.Client;
import com.ship.model.dto.NameDo;
import com.ship.security.model.RoleConst;
import com.ship.service.IClientService;
import com.ship.util.StrUtil;
import com.ship.util.UserUtil;
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
    public List<NameDo> getName() {
        return clientService.selectName();
    }

    /**
     * 【子查询】名下有宠物的用户，带上当前用户
     *
     * @param clientId 当前客户ID
     * @return 客户名称+ID
     */
    @GetMapping("/full")
    public List<NameDo> getNameHavePet(Integer clientId) {
        return clientService.selectNameHavePet(clientId);
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
        if (StrUtil.isWhite(client.getClientTel())) return Result.error("联系方式为空");
        return Result.choice("添加", clientService.addClient(client));
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
    @Secured(RoleConst.MANAGER)
    public Result update(@RequestBody Client client) {
        if (StrUtil.isWhite(client.getClientName())) return Result.error("用户名为空");
        if (StrUtil.isWhite(client.getClientUsername())) return Result.error("用户账号为空");
        if (StrUtil.isWhite(client.getClientTel())) return Result.error("联系方式为空");
        return Result.choice("修改", clientService.updateById(client));
    }

    /**
     * 用户修改自己的资料
     *
     * @param client 用户信息
     * @return 修改成功
     */
    @PutMapping("/self")
    @Secured(RoleConst.CLIENT)
    public Result updateSelf(@RequestBody Client client) {
        if (StrUtil.isWhite(client.getClientName())) return Result.error("用户名为空");
        if (StrUtil.isWhite(client.getClientUsername())) return Result.error("用户账号为空");
        if (UserUtil.getUser() instanceof Client client1) {
            client.setClientId(client1.getClientId());
            return Result.choice("修改", clientService.updateSelf(client));
        } else return Result.error("未得到登录人信息");
    }
}
