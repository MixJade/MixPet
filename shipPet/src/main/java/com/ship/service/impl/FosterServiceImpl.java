package com.ship.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ship.dto.FosterDto;
import com.ship.dto.FosterPetDto;
import com.ship.entity.Foster;
import com.ship.mapper.FosterMapper;
import com.ship.service.IFosterService;
import com.ship.util.CodeEnum;
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

    @Override
    public boolean deleteById(long fosterId) {
        String delDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return this.lambdaUpdate()
                .eq(Foster::getFosterId, fosterId)
                .set(Foster::getIsDel, delDate)
                .update();
    }

    @Override
    public boolean deleteByIds(List<Long> idGroup) {
        String delDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return this.lambdaUpdate()
                .in(Foster::getFosterId, idGroup)
                .set(Foster::getIsDel, delDate)
                .update();
    }

    @Override
    public IPage<FosterDto> selectByPage(String fosterCode, int numPage, int pageSize) {
        return baseMapper.selectFosterPage(new Page<>(numPage, pageSize), fosterCode);
    }

    @Override
    public IPage<FosterPetDto> fosterPet(int pageSize) {
        return baseMapper.fosterPet(new Page<>(1, pageSize));
    }

    @Override
    public Long haveFoster(Long petId) {
        // SELECT COUNT(*) FROM foster WHERE is_del='0' AND pet_id=? AND foster_term>=?
        return this.lambdaQuery()
                .eq(Foster::getPetId, petId)
                // ge:大于开始时间;le:小于等于结束时间
                .ge(Foster::getFosterTerm, LocalDate.now())
                .count();
    }

    @Override
    public boolean save(Foster entity) {
        entity.setFosterCode(CodeEnum.FOSTER.newCode(baseMapper.getMaxId()));
        return super.save(entity);
    }
}
