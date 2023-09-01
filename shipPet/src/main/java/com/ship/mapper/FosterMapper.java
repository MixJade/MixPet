package com.ship.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ship.model.dto.FosterDto;
import com.ship.model.dto.FosterPetDto;
import com.ship.model.entity.Foster;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 寄养表 Mapper 接口
 * </p>
 *
 * @author MixJade
 * @since 2023-01-12
 */
@Mapper
public interface FosterMapper extends BaseMapper<Foster> {
    IPage<FosterDto> selectFosterPage(IPage<FosterDto> page, @Param("clientName") String clientName, @Param("petName") String petName);

    @Select("select max(foster_id) from foster")
    Integer getMaxId();

    IPage<FosterPetDto> fosterPet(IPage<FosterDto> page);

    /**
     * 更新寄养表，且不更新订单号
     *
     * @param foster 寄养信息
     * @return 修改成功
     */
    boolean updateFoster(Foster foster);
}
