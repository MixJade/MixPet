package com.ship.mapper;

import com.ship.util.PageUtil;
import com.ship.dto.FosterDto;
import com.ship.dto.FosterPetDto;
import com.ship.entity.Foster;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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
    int selectFosterCount(@Param("fosterCode") String fosterCode);

    List<FosterDto> selectFosterPage(@Param("fosterCode") String fosterCode, @Param("pu") PageUtil pu);

    @Select("select max(foster_id) from foster")
    Long getMaxId();

    List<FosterPetDto> fosterPet();
}
