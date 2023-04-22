package com.forge.mapper;

import com.forge.common.PageUntil;
import com.forge.dto.FosterDto;
import com.forge.dto.FosterPetDto;
import com.forge.entity.Foster;
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
    boolean deleteId(@Param("delDate") String delDate, @Param("fosterId") long fosterId);

    boolean deleteIdGroup(@Param("delDate") String delDate, @Param("idGroup") long[] idGroup);

    int selectFosterCount(@Param("fosterCode") String fosterCode);

    List<FosterDto> selectFosterPage(@Param("fosterCode") String fosterCode, @Param("pu") PageUntil pu);

    @Select("select max(foster_id) from foster")
    Long getMaxId();

    List<FosterPetDto> fosterPet();
}
