package com.ship.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ship.model.dto.PetDto;
import com.ship.model.entity.Pet;
import com.ship.model.dto.NameDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 宠物信息表，外键用户表 Mapper 接口
 * </p>
 *
 * @author MixJade
 * @since 2022-12-22
 */
@Mapper
public interface PetMapper extends BaseMapper<Pet> {
    IPage<PetDto> selectPetPage(IPage<PetDto> page, @Param("petName") String petName, @Param("clientName") String clientName);

    IPage<Pet> selectFour(IPage<Pet> page);

    @Select("SELECT pet_id as roleId, pet_name as roleName FROM pet WHERE is_del=0")
    List<NameDo> selectName();

    /**
     * 「连接查询」用户名下的宠物，带当前数据
     *
     * @param clientId 用户ID
     * @param petId    当前的宠物ID
     * @return 用户名+ID
     */
    List<NameDo> selectByClient(Integer clientId, Integer petId);

    @Select("SELECT pet_id as roleId, pet_name as roleName FROM pet WHERE is_del = '0' AND isNull(client_id)")
    List<NameDo> selectNoClient();

    /**
     * 这种使用对象作为唯一参数
     * <p>在xml中，可以直接使用属性名</p>
     * <p>甚至这样更新时间也会自动注入</p>
     *
     * @param pet 宠物信息
     * @return 修改成功
     */
    boolean updatePet(Pet pet);
}
