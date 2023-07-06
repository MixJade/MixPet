package com.ship.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ship.dto.FosterDto;
import com.ship.dto.FosterPetDto;
import com.ship.entity.Foster;
import com.ship.mapper.FosterMapper;
import com.ship.service.IFosterService;
import com.ship.util.CodeEnum;
import com.ship.util.PageUtil;
import com.ship.vo.Page;
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
    public Page<FosterDto> selectByPage(String fosterCode, int numPage, int pageSize) {
        int maxCount = baseMapper.selectFosterCount(fosterCode);
        PageUtil pu = PageUtil.pu(numPage, pageSize, maxCount);
        var fosterList = baseMapper.selectFosterPage(fosterCode, pu);
        return new Page<>(fosterList, maxCount);
    }

    @Override
    public List<FosterPetDto> fosterPet() {
        return baseMapper.fosterPet();
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
