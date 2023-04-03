package com.forge.mapper;

import com.forge.dto.AdoptDto;
import com.forge.entity.Adopt;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 领养宠物订单 Mapper 接口
 * </p>
 *
 * @author MixJade
 * @since 2023-01-12
 */
@Mapper
public interface AdoptMapper extends BaseMapper<Adopt> {
    boolean deleteId(@Param("delDate") String delDate, @Param("adoptId") long adoptId);

    boolean deleteIdGroup(@Param("delDate") String delDate, @Param("idGroup") long[] idGroup);

    int selectAdoptCount(@Param("adoptCode") String adoptCode);

    List<AdoptDto> selectAdoptPage(@Param("adoptCode") String adoptCode, @Param("begin") int begin, @Param("pageItem") int pageItem);

    @Select("select max(adopt_id) from adopt")
    Long getMaxId();

    boolean adoptAdopt(@Param("adoptId") Long adoptId, @Param("petInfo") String petInfo);

    /**
     * 查询是否已有待审核订单
     */
    int sureInAdopt(@Param("petId") Long petId, @Param("clientId") Long clientId);

    /**
     * 查询宠物是否有主人
     */
    @Select("select a.client_id from pet as a ,adopt as b where a.pet_id=b.pet_id and b.adopt_id=#{adoptId}")
    Long petMaster(Long adoptId);
}
