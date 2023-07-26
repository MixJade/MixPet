package com.ship.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ship.entity.Client;
import com.ship.mapper.ClientMapper;
import com.ship.service.IClientService;
import com.ship.vo.NameVo;
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
    public boolean deleteById(long clientId) {
        return this.lambdaUpdate()
                .eq(Client::getClientId, clientId)
                .set(Client::getIsDel, String.valueOf(System.currentTimeMillis()))
                .update();
    }

    @Override
    public boolean deleteByIds(List<Long> idGroup) {
        return this.lambdaUpdate()
                .in(Client::getClientId, idGroup)
                .set(Client::getIsDel, String.valueOf(System.currentTimeMillis()))
                .update();
    }

    @Override
    public IPage<Client> selectByPage(String clientName, int numPage, int pageSize) {
        return baseMapper.selectClientPage(new Page<>(numPage, pageSize), clientName);
    }

    @Override
    public List<NameVo> selectName() {
        return baseMapper.selectName();
    }
}
