package com.forge.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.forge.common.PageUntil;
import com.forge.dto.NoticeDto;
import com.forge.entity.Notice;
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
    @Select("select client_photo from client WHERE client_photo IS NOT NULL AND client_photo != ''")
    List<String> selectImgClient();

    @Select("select doctor_photo from doctor WHERE doctor_photo IS NOT NULL AND doctor_photo != ''")
    List<String> selectImgDoctor();

    @Select("select pet_photo as a from pet WHERE pet_photo IS NOT NULL AND pet_photo != ''")
    List<String> selectImgPet();

    @Select("select employee_photo as a from employee WHERE employee_photo IS NOT NULL AND employee_photo != ''")
    List<String> selectImgEmployee();

    @Select("select notice_file as a from notice WHERE notice_file IS NOT NULL AND notice_file != '' AND is_del='0'")
    List<String> selectNoticeFile();

    @Select("select msg_content from msg where is_img='1' and is_del='0'")
    List<String> selectImgChat();

    boolean deleteId(@Param("delDate") String delDate, @Param("noticeId") long noticeId);

    boolean deleteIdGroup(@Param("delDate") String delDate, @Param("idGroup") long[] idGroup);

    int selectNoticeCount(@Param("noticeName") String noticeName);

    List<NoticeDto> selectNoticePage(@Param("noticeName") String noticeName, @Param("pu") PageUntil pu);

    List<NoticeDto> selectFour();

    boolean updateNotice(@Param("updateId") Long updateId, @Param("noticeTitle") String noticeTitle, @Param("noticeId") long noticeId);

    boolean addNotice(@Param("creatId") Long creatId, @Param("noticeTitle") String noticeTitle, @Param("noticeFile") String noticeFile);

    @Update("update notice set is_disable = #{isDis} where notice_id=#{id}")
    boolean disableNotice(@Param("id") long noticeId, @Param("isDis") boolean isDis);
}