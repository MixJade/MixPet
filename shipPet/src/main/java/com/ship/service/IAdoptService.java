package com.ship.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ship.common.Result;
import com.ship.model.dto.AdoptDto;
import com.ship.model.entity.Adopt;
import com.ship.model.vo.AdoptVo;

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
    boolean deleteById(Integer adoptId);

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
     * @param clientName 用户名
     * @param petName    宠物名
     * @param numPage    当前页码
     * @param pageSize   当前一页的最大条数
     * @return 数据的总长度，与分页查询结果
     */
    IPage<AdoptDto> selectByPage(String clientName, String petName, int numPage, int pageSize);

    /**
     * 审核领养单
     *
     * @param adoptVo 领养表ID、是否通过
     * @return 审核结果
     */
    Result adoptAdopt(AdoptVo adoptVo);

    /**
     * 确认当前宠物是否有待审核订单
     *
     * @param petId    待领养宠物id
     * @param clientId 当前登录用户id
     * @return 订单数量
     */
    int sureInAdopt(Integer petId, Integer clientId);
}
