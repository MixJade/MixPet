package com.ship.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ship.model.dto.AdoptDto;
import com.ship.model.entity.Adopt;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
    IPage<AdoptDto> selectAdoptPage(IPage<AdoptDto> page, @Param("clientName") String clientName, @Param("petName") String petName);

    @Select("select max(adopt_id) from adopt")
    Integer getMaxId();

    boolean adoptAdopt(@Param("adoptId") Integer adoptId, @Param("petInfo") String petInfo);

    /**
     * 查询是否已有待审核订单
     */
    int sureInAdopt(@Param("petId") Integer petId, @Param("clientId") Integer clientId);

    /**
     * 查询宠物是否有主人
     */
    @Select("""
            SELECT
            	a.client_id
            FROM
            	pet AS a,
            	adopt AS b
            WHERE
            	a.pet_id = b.pet_id
            	AND b.adopt_id = #{adoptId}
            """)
    Integer petMaster(Integer adoptId);
}
