package com.ship.service;

import com.ship.dto.NoticeDto;
import com.ship.vo.Page;
import com.ship.entity.Notice;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 公告表 服务类
 * </p>
 *
 * @author MixJade
 * @since 2023-01-02
 */
public interface INoticeService extends IService<Notice> {
    /**
     * 去掉冗余的图片
     *
     * @return 删除成功
     */
    boolean delImg();

    /**
     * 单个逻辑删除
     *
     * @param petId 宠物id
     * @return 是否删除成功
     */
    boolean deleteById(long petId);

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
     * @param noticeName 公告名
     * @param numPage    当前页码
     * @param pageSize   当前一页的最大条数
     * @return 数据的总长度，与分页查询结果
     */
    Page<NoticeDto> selectByPage(String noticeName, int numPage, int pageSize);

    /**
     * 主页的公告查询
     */
    List<NoticeDto> selectFour();

    /**
     * 添加公告
     *
     * @param noticeDto 公告内容，标题，创建人ID
     * @return 添加成功
     */
    boolean addNotice(NoticeDto noticeDto);

    /**
     * 修改公告
     *
     * @param noticeDto 公告内容，标题，文件名，修改人ID
     * @return 修改成功
     */
    boolean updateNotice(NoticeDto noticeDto);

    /**
     * 禁用公告
     *
     * @param noticeId 公告id
     * @return 禁用成功
     */
    boolean disableNotice(long noticeId, boolean isDis);

}
