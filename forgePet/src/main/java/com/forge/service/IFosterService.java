package com.forge.service;

import com.forge.dto.FosterDto;
import com.forge.dto.FosterPetDto;
import com.forge.vo.Page;
import com.forge.entity.Foster;
import com.baomidou.mybatisplus.extension.service.IService;

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
    boolean deleteByIds(long[] idGroup);

    /**
     * 分页查询
     *
     * @param fosterCode 订单编码
     * @param numPage    当前页码
     * @param pageSize   当前一页的最大条数
     * @return 数据的总长度，与分页查询结果
     */
    Page<List<FosterDto>> selectByPage(String fosterCode, int numPage, int pageSize);

    /**
     * 查询被寄养宠物信息
     * @return 被寄养宠物信息
     */
    List<FosterPetDto> fosterPet();
}
