package com.forge.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forge.common.PageUntil;
import com.forge.vo.NameVo;
import com.forge.vo.Page;
import com.forge.dto.PetDto;
import com.forge.entity.Pet;
import com.forge.mapper.PetMapper;
import com.forge.service.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final PetMapper petMapper;

    @Autowired
    public PetServiceImpl(PetMapper petMapper) {
        this.petMapper = petMapper;
    }

    @Override
    public boolean deleteById(long petId) {
        String delDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return petMapper.deleteId(delDate, petId);
    }

    @Override
    public boolean deleteByIds(long[] idGroup) {
        String delDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return petMapper.deleteIdGroup(delDate, idGroup);
    }

    @Override
    public Page<List<PetDto>> selectByPage(String petName, String clientName, int numPage, int pageSize) {
        int maxCount = petMapper.selectPetCount(petName, clientName);
        PageUntil pu = PageUntil.pu(numPage, pageSize, maxCount);
        var petList = petMapper.selectPetPage(petName, clientName, pu);
        return new Page<>(petList, maxCount);
    }

    @Override
    public Page<List<Pet>> selectFour(int numPage, int pageSize) {
        int maxCount = petMapper.selectFourNum();
        int needBegin = (numPage - 1) * pageSize;
        if (needBegin >= maxCount) needBegin = (maxCount / pageSize - 1) * pageSize;
        return new Page<>(petMapper.selectFour(needBegin, pageSize), maxCount);
    }

    @Override
    public boolean updatePet(Pet pet) {
        return petMapper.updatePet(pet);
    }

    @Override
    public List<NameVo> selectName() {
        return petMapper.selectName();
    }

    @Override
    public List<NameVo> selectByClient(long clientId) {
        return petMapper.selectByClient(clientId);
    }

    @Override
    public List<NameVo> selectNoClient() {
        return petMapper.selectNoClient();
    }
}
