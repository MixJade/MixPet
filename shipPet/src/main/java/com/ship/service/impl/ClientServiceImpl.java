package com.ship.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ship.common.PhotoEnum;
import com.ship.mapper.ClientMapper;
import com.ship.model.entity.Client;
import com.ship.model.dto.NameDo;
import com.ship.service.IClientService;
import com.ship.util.StrUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author MixJade
 * @since 2022-12-22
 */
@Service
public class ClientServiceImpl extends ServiceImpl<ClientMapper, Client> implements IClientService {

    @Override
    public boolean deleteById(Integer clientId) {
        return this.lambdaUpdate()
                .eq(Client::getClientId, clientId)
                .set(Client::getIsDel, System.currentTimeMillis())
                .update();
    }

    @Override
    public boolean deleteByIds(List<Integer> idGroup) {
        return this.lambdaUpdate()
                .in(Client::getClientId, idGroup)
                .set(Client::getIsDel, System.currentTimeMillis())
                .update();
    }

    @Override
    public IPage<Client> selectByPage(String clientName, int numPage, int pageSize) {
        return baseMapper.selectClientPage(new Page<>(numPage, pageSize), clientName);
    }

    @Override
    public List<NameDo> selectName() {
        return baseMapper.selectName();
    }

    /**
     * 【子查询】名下有宠物的用户，带上当前用户
     *
     * @param clientId 当前客户ID
     * @return 客户名称+ID
     */
    @Override
    public List<NameDo> selectNameHavePet(Integer clientId) {
        return baseMapper.selectNameHavePet(clientId);
    }

    @Override
    public boolean addClient(Client client) {
        if (StrUtil.isWhite(client.getClientPhoto())) client.setClientPhoto(PhotoEnum.CLIENT.getPhotoName());
        if (StrUtil.isWhite(client.getClientInfo())) client.setClientInfo("该用户暂无简介");
        if (StrUtil.isWhite(client.getClientPassword())) client.setClientPassword("123456");
        client.setClientPassword(StrUtil.tranPwd(client.getClientPassword()));
        return save(client);
    }

    @Override
    public boolean updateSelf(Client client) {
        return baseMapper.updateSelfById(client);
    }
}
