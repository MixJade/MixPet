package com.forge.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forge.common.CodeEnum;
import com.forge.dto.AdoptDto;
import com.forge.vo.Page;
import com.forge.entity.Adopt;
import com.forge.mapper.AdoptMapper;
import com.forge.service.IAdoptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 领养宠物订单 服务实现类
 * </p>
 *
 * @author MixJade
 * @since 2023-01-12
 */
@Service
public class AdoptServiceImpl extends ServiceImpl<AdoptMapper, Adopt> implements IAdoptService {
    private final AdoptMapper adoptMapper;

    @Autowired
    public AdoptServiceImpl(AdoptMapper adoptMapper) {
        this.adoptMapper = adoptMapper;
    }

    @Override
    public boolean deleteById(long adoptId) {
        String delDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return adoptMapper.deleteId(delDate, adoptId);
    }

    @Override
    public boolean deleteByIds(long[] idGroup) {
        String delDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return adoptMapper.deleteIdGroup(delDate, idGroup);
    }

    @Override
    public Page<List<AdoptDto>> selectByPage(String adoptCode, int numPage, int pageSize) {
        int maxCount = adoptMapper.selectAdoptCount(adoptCode);
        int needBegin = (numPage - 1) * pageSize;
        if (needBegin >= maxCount) needBegin = (maxCount / pageSize - 1) * pageSize;
        var adoptList = adoptMapper.selectAdoptPage(adoptCode, needBegin, pageSize);
        return new Page<>(adoptList, maxCount);
    }

    @Override
    public boolean adoptAdopt(Long adoptId, String petInfo) {
        return adoptMapper.adoptAdopt(adoptId, petInfo);
    }

    @Override
    public int sureInAdopt(Long petId, Long clientId) {
        return adoptMapper.sureInAdopt(petId, clientId);
    }

    @Override
    public boolean petMaster(Long adoptId) {
        return adoptMapper.petMaster(adoptId) == null;
    }

    @Override
    public boolean save(Adopt entity) {
        entity.setAdoptCode(CodeEnum.ADOPT.newCode(adoptMapper.getMaxId()));
        return super.save(entity);
    }
}
