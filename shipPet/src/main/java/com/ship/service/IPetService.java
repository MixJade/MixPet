package com.ship.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ship.model.dto.PetDto;
import com.ship.model.entity.Pet;
import com.ship.model.vo.NameVo;

import java.util.List;

/**
 * <p>
 * 宠物信息表，外键用户表 服务类
 * </p>
 *
 * @author MixJade
 * @since 2022-12-22
 */
public interface IPetService extends IService<Pet> {
    /**
     * 单个逻辑删除
     *
     * @param petId 宠物id
     * @return 是否删除成功
     */
    boolean deleteById(Integer petId);

    /**
     * 批量逻辑删除
     *
     * @param idGroup 一组id
     * @return 是否批量删除成功
     */
    boolean deleteByIds(List<Integer> idGroup);

    /**
     * 分页查询
     *
     * @param petName    宠物姓名，记得加模糊判别条件
     * @param clientName 主人名，同上
     * @param numPage    当前页码
     * @param pageSize   当前一页的最大条数
     * @return 数据的总长度，与分页查询结果
     */
    IPage<PetDto> selectByPage(String petName, String clientName, int numPage, int pageSize);

    /**
     * 主页的待领养宠物查询
     */
    IPage<Pet> selectFour(int numPage, int pageSize);

    /**
     * 更新宠物信息，让主人id可以为null
     *
     * @param pet 宠物信息
     * @return 是否更新成功
     */
    boolean updatePet(Pet pet);

    /**
     * 查询名字与对应id
     *
     * @return 相应角色名字与id
     */
    List<NameVo> selectName();

    /**
     * 查询主人对应的宠物
     */
    List<NameVo> selectByClient(Integer clientId);

    /**
     * 查询没有主人的宠物
     */
    List<NameVo> selectNoClient();
}
