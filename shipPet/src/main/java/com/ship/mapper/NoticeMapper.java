package com.ship.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ship.dto.NoticeDto;
import com.ship.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * 公告表 Mapper 接口
 * </p>
 *
 * @author MixJade
 * @since 2023-01-02
 */
@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {
    List<String> selectImgAvatar();

    @Select("select notice_file as a from notice WHERE notice_file IS NOT NULL AND notice_file != '' AND is_del='0'")
    List<String> selectNoticeFile();

    @Select("select msg_content from msg where is_img='1' and is_del='0'")
    List<String> selectImgChat();

    IPage<NoticeDto> selectNoticePage(IPage<NoticeDto> page, @Param("noticeName") String noticeName);

    List<NoticeDto> selectFour();

    boolean updateNotice(@Param("updateId") Long updateId, @Param("noticeTitle") String noticeTitle, @Param("noticeId") long noticeId);

    boolean addNotice(@Param("creatId") Long creatId, @Param("noticeTitle") String noticeTitle, @Param("noticeFile") String noticeFile);

    @Update("update notice set is_disable = #{isDis} where notice_id=#{id}")
    boolean disableNotice(@Param("id") long noticeId, @Param("isDis") boolean isDis);
}