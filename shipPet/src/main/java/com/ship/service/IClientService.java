package com.ship.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ship.vo.NameVo;
import com.ship.vo.Page;
import com.ship.entity.Client;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author MixJade
 * @since 2022-12-22
 */
public interface IClientService extends IService<Client> {
    /**
     * 单个逻辑删除
     *
     * @param clientId 客户id
     * @return 是否删除成功
     */
    boolean deleteById(long clientId);

    /**
     * 批量逻辑删除
     *
     * @param idGroup 一组id
     * @return 是否批量删除成功
     */
    boolean deleteByIds(long[] idGroup);

    /**
     * 分页查询
     *
     * @param clientName 客户姓名，记得加模糊判别条件
     * @param numPage    当前页码
     * @param pageSize   当前一页的最大条数
     * @return 数据的总长度，与分页查询结果
     */
    Page<Client> selectByPage(String clientName, int numPage, int pageSize);

    /**
     * 查询所有用户的姓名及对应id
     *
     * @return 所有用户名字及id
     */
    List<NameVo> selectName();
}
