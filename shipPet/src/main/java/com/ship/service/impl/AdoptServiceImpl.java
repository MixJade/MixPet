package com.ship.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ship.common.Result;
import com.ship.mapper.AdoptMapper;
import com.ship.model.dto.AdoptDto;
import com.ship.model.entity.Adopt;
import com.ship.model.vo.AdoptVo;
import com.ship.service.IAdoptService;
import com.ship.util.CodeEnum;
import org.springframework.stereotype.Service;

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
    public boolean deleteById(Integer adoptId) {
        return this.lambdaUpdate()
                .eq(Adopt::getAdoptId, adoptId)
                .set(Adopt::getIsDel, System.currentTimeMillis())
                .update();
    }

    @Override
    public boolean deleteByIds(List<Integer> idGroup) {
        return this.lambdaUpdate()
                .in(Adopt::getAdoptId, idGroup)
                .set(Adopt::getIsDel, System.currentTimeMillis())
                .update();
    }

    @Override
    public IPage<AdoptDto> selectByPage(String clientName, String petName, int numPage, int pageSize) {
        return baseMapper.selectAdoptPage(new Page<>(numPage, pageSize), clientName, petName);
    }

    @Override
    public Result adoptAdopt(AdoptVo adoptVo) {
        // 查看是否有主
        if (baseMapper.petMaster(adoptVo.adoptId()) > 0) return Result.error("此物有主");
        // 无主则判断是否正常操作
        if (adoptVo.isAdopt() == null) return Result.error("你不对劲");
        // 正常操作则进行同意or拒绝
        if (adoptVo.isAdopt()) {
            String petInfo = "它有了温暖的家，感恩";
            return Result.choice("领养", baseMapper.adoptAdopt(adoptVo.adoptId(), petInfo));
        } else {
            return Result.choice("拒绝", baseMapper.refuse(adoptVo.adoptId()));
        }
    }

    @Override
    public int sureInAdopt(Integer petId, Integer clientId) {
        return baseMapper.sureInAdopt(petId, clientId);
    }

    @Override
    public boolean save(Adopt entity) {
        entity.setAdoptCode(CodeEnum.ADOPT.newCode(baseMapper.getMaxId()));
        return super.save(entity);
    }
}
