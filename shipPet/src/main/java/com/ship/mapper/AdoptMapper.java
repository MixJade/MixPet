package com.ship.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ship.model.dto.AdoptDto;
import com.ship.model.entity.Adopt;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
    /**
     * 像这种，xml文件中的占位符名称与接口参数名称一样的
     * <p>可以不用通过Param注解起别名</p>
     *
     * @param page       mp的分页插件
     * @param clientName 用户名
     * @param petName    宠物名
     * @return 领养信息
     */
    IPage<AdoptDto> selectAdoptPage(IPage<AdoptDto> page, String clientName, String petName);

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
            	count(a.client_id)
            FROM
            	pet AS a,
            	adopt AS b
            WHERE
            	a.pet_id = b.pet_id
            	AND b.adopt_id = #{adoptId}
            """)
    int petMaster(Integer adoptId);

    /**
     * 拒绝领养申请
     * <p>增删改操作是可以直接返回boolean的</p>
     * <p>另外，特别简单、流程专属的，建议用mapper注解</p>
     *
     * @param adoptId 领养单id
     * @return 拒绝成功
     */
    @Update("UPDATE adopt SET in_adopt = 0 WHERE adopt_id = #{adoptId}")
    boolean refuse(Integer adoptId);
}
