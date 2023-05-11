package com.forge.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forge.util.PageUtil;
import com.forge.vo.NameVo;
import com.forge.vo.Page;
import com.forge.entity.Client;
import com.forge.mapper.ClientMapper;
import com.forge.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final ClientMapper clientMapper;

    @Autowired
    public ClientServiceImpl(ClientMapper clientMapper) {
        this.clientMapper = clientMapper;
    }

    @Override
    public boolean deleteById(long clientId) {
        String delDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return clientMapper.deleteId(delDate, clientId);
    }

    @Override
    public boolean deleteByIds(long[] idGroup) {
        String delDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return clientMapper.deleteIdGroup(delDate, idGroup);
    }

    @Override
    public Page<Client> selectByPage(String clientName, int numPage, int pageSize) {
        int maxCount = clientMapper.selectClientCount(clientName);
        PageUtil pu = PageUtil.pu(numPage, pageSize, maxCount);
        var clientList = clientMapper.selectClientPage(clientName, pu);
        return new Page<>(clientList, maxCount);
    }

    @Override
    public List<NameVo> selectName() {
        return clientMapper.selectName();
    }
}
