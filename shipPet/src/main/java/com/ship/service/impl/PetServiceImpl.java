package com.ship.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ship.model.dto.PetDto;
import com.ship.model.entity.Pet;
import com.ship.mapper.PetMapper;
import com.ship.service.IPetService;
import com.ship.model.vo.NameVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 宠物信息表，外键用户表 服务实现类
 * </p>
 *
 * @author MixJade
 * @since 2022-12-22
 */
@Service
public class PetServiceImpl extends ServiceImpl<PetMapper, Pet> implements IPetService {

    @Override
    public boolean deleteById(Integer petId) {
        return this.lambdaUpdate()
                .eq(Pet::getPetId, petId)
                .set(Pet::getIsDel, System.currentTimeMillis())
                .update();
    }

    @Override
    public boolean deleteByIds(List<Integer> idGroup) {
        return this.lambdaUpdate()
                .in(Pet::getPetId, idGroup)
                .set(Pet::getIsDel, System.currentTimeMillis())
                .update();
    }

    @Override
    public IPage<PetDto> selectByPage(String petName, String clientName, int numPage, int pageSize) {
        return baseMapper.selectPetPage(new Page<>(numPage, pageSize), petName, clientName);
    }

    @Override
    public IPage<Pet> selectFour(int numPage, int pageSize) {
        return baseMapper.selectFour(new Page<>(numPage, pageSize));
    }

    @Override
    public boolean updatePet(Pet pet) {
        return baseMapper.updatePet(pet);
    }

    @Override
    public List<NameVo> selectName() {
        return baseMapper.selectName();
    }

    @Override
    public List<NameVo> selectByClient(Integer clientId) {
        return baseMapper.selectByClient(clientId);
    }

    @Override
    public List<NameVo> selectNoClient() {
        return baseMapper.selectNoClient();
    }
}
