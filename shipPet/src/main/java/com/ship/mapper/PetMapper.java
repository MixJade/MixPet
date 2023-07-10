package com.ship.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ship.dto.PetDto;
import com.ship.entity.Pet;
import com.ship.vo.NameVo;
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

    @Select("SELECT pet_id as roleId, pet_name as roleName FROM pet WHERE is_del = '0'")
    List<NameVo> selectName();

    @Select("SELECT pet_id as roleId, pet_name as roleName FROM pet WHERE is_del = '0' AND client_id=#{clientId}")
    List<NameVo> selectByClient(long clientId);

    @Select("SELECT pet_id as roleId, pet_name as roleName FROM pet WHERE is_del = '0' AND isNull(client_id)")
    List<NameVo> selectNoClient();

    boolean updatePet(Pet pet);
}
