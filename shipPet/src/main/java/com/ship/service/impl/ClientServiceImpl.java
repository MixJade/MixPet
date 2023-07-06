package com.ship.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ship.entity.Client;
import com.ship.mapper.ClientMapper;
import com.ship.service.IClientService;
import com.ship.util.PageUtil;
import com.ship.vo.NameVo;
import com.ship.vo.Page;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        String delDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return this.lambdaUpdate()
                .eq(Client::getClientId, clientId)
                .set(Client::getIsDel, delDate)
                .update();
    }

    @Override
    public boolean deleteByIds(List<Long> idGroup) {
        String delDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return this.lambdaUpdate()
                .in(Client::getClientId, idGroup)
                .set(Client::getIsDel, delDate)
                .update();
    }

    @Override
    public Page<Client> selectByPage(String clientName, int numPage, int pageSize) {
        int maxCount = baseMapper.selectClientCount(clientName);
        PageUtil pu = PageUtil.pu(numPage, pageSize, maxCount);
        var clientList = baseMapper.selectClientPage(clientName, pu);
        return new Page<>(clientList, maxCount);
    }

    @Override
    public List<NameVo> selectName() {
        return baseMapper.selectName();
    }
}
