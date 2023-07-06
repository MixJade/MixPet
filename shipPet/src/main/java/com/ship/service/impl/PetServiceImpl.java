package com.ship.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ship.dto.PetDto;
import com.ship.entity.Pet;
import com.ship.mapper.PetMapper;
import com.ship.service.IPetService;
import com.ship.util.PageUtil;
import com.ship.vo.NameVo;
import com.ship.vo.Page;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    public boolean deleteById(long petId) {
        String delDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return this.lambdaUpdate()
                .eq(Pet::getPetId, petId)
                .set(Pet::getIsDel, delDate)
                .update();
    }

    @Override
    public boolean deleteByIds(List<Long> idGroup) {
        String delDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return this.lambdaUpdate()
                .in(Pet::getPetId, idGroup)
                .set(Pet::getIsDel, delDate)
                .update();
    }

    @Override
    public Page<PetDto> selectByPage(String petName, String clientName, int numPage, int pageSize) {
        int maxCount = baseMapper.selectPetCount(petName, clientName);
        PageUtil pu = PageUtil.pu(numPage, pageSize, maxCount);
        var petList = baseMapper.selectPetPage(petName, clientName, pu);
        return new Page<>(petList, maxCount);
    }

    @Override
    public Page<Pet> selectFour(int numPage, int pageSize) {
        int maxCount = baseMapper.selectFourNum();
        PageUtil pu = PageUtil.pu(numPage, pageSize, maxCount);
        return new Page<>(baseMapper.selectFour(pu), maxCount);
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
    public List<NameVo> selectByClient(long clientId) {
        return baseMapper.selectByClient(clientId);
    }

    @Override
    public List<NameVo> selectNoClient() {
        return baseMapper.selectNoClient();
    }
}
