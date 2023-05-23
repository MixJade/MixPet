package com.forge.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forge.util.CodeEnum;
import com.forge.util.PageUtil;
import com.forge.dto.FosterDto;
import com.forge.dto.FosterPetDto;
import com.forge.vo.Page;
import com.forge.entity.Foster;
import com.forge.mapper.FosterMapper;
import com.forge.service.IFosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 寄养表 服务实现类
 * </p>
 *
 * @author MixJade
 * @since 2023-01-12
 */
@Service
public class FosterServiceImpl extends ServiceImpl<FosterMapper, Foster> implements IFosterService {
    private final FosterMapper fosterMapper;

    @Autowired
    public FosterServiceImpl(FosterMapper fosterMapper) {
        this.fosterMapper = fosterMapper;
    }

    @Override
    public boolean deleteById(long fosterId) {
        String delDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return fosterMapper.deleteId(delDate, fosterId);
    }

    @Override
    public boolean deleteByIds(long[] idGroup) {
        String delDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return fosterMapper.deleteIdGroup(delDate, idGroup);
    }

    @Override
    public Page<FosterDto> selectByPage(String fosterCode, int numPage, int pageSize) {
        int maxCount = fosterMapper.selectFosterCount(fosterCode);
        PageUtil pu = PageUtil.pu(numPage, pageSize, maxCount);
        var fosterList = fosterMapper.selectFosterPage(fosterCode, pu);
        return new Page<>(fosterList, maxCount);
    }

    @Override
    public List<FosterPetDto> fosterPet() {
        return fosterMapper.fosterPet();
    }

    @Override
    public Long haveFoster(Long petId) {
        var queryWrapper=new LambdaQueryWrapper<Foster>();
        queryWrapper.eq(Foster::getPetId,petId);
        // ge:大于开始时间;le:小于等于结束时间
        queryWrapper.ge(Foster::getFosterTerm, LocalDate.now());
        // SELECT COUNT(*) FROM foster WHERE is_del='0' AND pet_id=? AND foster_term>=?
        return fosterMapper.selectCount(queryWrapper);
    }

    @Override
    public boolean save(Foster entity) {
        entity.setFosterCode(CodeEnum.FOSTER.newCode(fosterMapper.getMaxId()));
        return super.save(entity);
    }
}
