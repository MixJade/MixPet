package com.ship.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ship.dto.AdoptDto;
import com.ship.entity.Adopt;
import com.ship.mapper.AdoptMapper;
import com.ship.service.IAdoptService;
import com.ship.util.CodeEnum;
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

    @Override
    public boolean deleteById(long adoptId) {
        String delDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return this.lambdaUpdate()
                .eq(Adopt::getAdoptId, adoptId)
                .set(Adopt::getIsDel, delDate)
                .update();
    }

    @Override
    public boolean deleteByIds(List<Long> idGroup) {
        String delDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return this.lambdaUpdate()
                .in(Adopt::getAdoptId, idGroup)
                .set(Adopt::getIsDel, delDate)
                .update();
    }

    @Override
    public IPage<AdoptDto> selectByPage(String adoptCode, int numPage, int pageSize) {
        return baseMapper.selectAdoptPage(new Page<>(numPage, pageSize), adoptCode);
    }

    @Override
    public boolean adoptAdopt(Long adoptId, String petInfo) {
        return baseMapper.adoptAdopt(adoptId, petInfo);
    }

    @Override
    public int sureInAdopt(Long petId, Long clientId) {
        return baseMapper.sureInAdopt(petId, clientId);
    }

    @Override
    public boolean petMaster(Long adoptId) {
        return baseMapper.petMaster(adoptId) == null;
    }

    @Override
    public boolean save(Adopt entity) {
        entity.setAdoptCode(CodeEnum.ADOPT.newCode(baseMapper.getMaxId()));
        return super.save(entity);
    }
}
