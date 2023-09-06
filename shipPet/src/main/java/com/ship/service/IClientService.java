package com.ship.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ship.model.entity.Client;
import com.ship.model.dto.NameDo;

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
    boolean deleteById(Integer clientId);

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
     * @param clientName 客户姓名，记得加模糊判别条件
     * @param numPage    当前页码
     * @param pageSize   当前一页的最大条数
     * @return 数据的总长度，与分页查询结果
     */
    IPage<Client> selectByPage(String clientName, int numPage, int pageSize);

    /**
     * 查询所有用户的姓名及对应id
     *
     * @return 所有用户名字及id
     */
    List<NameDo> selectName();

    /**
     * 【子查询】名下有宠物的用户，带上当前用户
     *
     * @param clientId 当前客户ID
     * @return 客户名称+ID
     */
    List<NameDo> selectNameHavePet(Integer clientId);

    /**
     * 添加用户，关于加密与默认值
     *
     * @param client 用户信息
     * @return 添加成功
     */
    boolean addClient(Client client);

    /**
     * 用户修改自己的资料
     *
     * @param client 用户信息
     * @return 修改成功
     */
    boolean updateSelf(Client client);
}
