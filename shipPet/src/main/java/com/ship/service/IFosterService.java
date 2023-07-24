package com.ship.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ship.dto.FosterDto;
import com.ship.dto.FosterPetDto;
import com.ship.entity.Foster;

import java.util.List;

/**
 * <p>
 * 寄养表 服务类
 * </p>
 *
 * @author MixJade
 * @since 2023-01-12
 */
public interface IFosterService extends IService<Foster> {
    /**
     * 单个逻辑删除
     *
     * @param fosterId 寄养单id
     * @return 是否删除成功
     */
    boolean deleteById(long fosterId);

    /**
     * 批量逻辑删除
     *
     * @param idGroup 一组id
     * @return 是否批量删除成功
     */
    boolean deleteByIds(List<Long> idGroup);

    /**
     * 分页查询
     *
     * @param clientName 用户名
     * @param petName    宠物名
     * @param numPage    当前页码
     * @param pageSize   当前一页的最大条数
     * @return 数据的总长度，与分页查询结果
     */
    IPage<FosterDto> selectByPage(String clientName, String petName, int numPage, int pageSize);

    /**
     * 用户前台：查询被寄养宠物信息
     *
     * @return 被寄养宠物的卡片信息
     */
    IPage<FosterPetDto> fosterPet(int pageSize);

    /**
     * 在新增寄养信息时，查询是否已有在寄养中的订单
     *
     * @param petId 待寄养宠物的ID
     * @return 订单数量
     */
    Long haveFoster(Long petId);
}
