package com.ship.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ship.dto.AdoptDto;
import com.ship.entity.Adopt;

import java.util.List;

/**
 * <p>
 * 领养宠物订单 服务类
 * </p>
 *
 * @author MixJade
 * @since 2023-01-12
 */
public interface IAdoptService extends IService<Adopt> {
    /**
     * 单个逻辑删除
     *
     * @param adoptId 寄养单id
     * @return 是否删除成功
     */
    boolean deleteById(long adoptId);

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
     * @param adoptCode 订单编码
     * @param numPage   当前页码
     * @param pageSize  当前一页的最大条数
     * @return 数据的总长度，与分页查询结果
     */
    IPage<AdoptDto> selectByPage(String adoptCode, int numPage, int pageSize);

    /**
     * 审核通过之后更新相应宠物表
     *
     * @param adoptId 领养表ID
     * @param petInfo 宠物信息
     * @return 更新成功
     */
    boolean adoptAdopt(Long adoptId, String petInfo);

    /**
     * 确认当前宠物是否有待审核订单
     *
     * @param petId    待领养宠物id
     * @param clientId 当前登录用户id
     * @return 订单数量
     */
    int sureInAdopt(Long petId, Long clientId);

    /**
     * 查询宠物是否有主人
     */
    boolean petMaster(Long adoptId);
}
