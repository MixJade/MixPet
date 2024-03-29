package com.ship.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ship.entity.Msg;
import com.ship.vo.MsgNameVo;
import com.ship.vo.MsgVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 聊天记录 Mapper 接口
 * </p>
 *
 * @author MixJade
 * @since 2023-02-25
 */
@Mapper
public interface MsgMapper extends BaseMapper<Msg> {
    /**
     * 查询用户最近谈话的医生
     */
    Integer getByClient(@Param("clientId") Integer clientId);

    /**
     * 查询特定用户与医生对话
     */
    List<MsgVo> getADoctor(@Param("clientId") Integer clientId, @Param("doctorId") Integer doctorId);

    /**
     * 查询与用户有过联系的医生
     */
    List<MsgNameVo> getDoctorGroup(@Param("clientId") Integer clientId, @Param("doctorId") Integer doctorId);

    /**
     * 查询医生最近谈话的用户
     */
    Integer getByDoctor(@Param("doctorId") Integer doctorId);

    /**
     * 查询特定医生与用户对话
     */
    List<MsgVo> getAClient(@Param("doctorId") Integer doctorId, @Param("clientId") Integer clientId);

    /**
     * 医生谈过话的用户
     */
    List<MsgNameVo> getClientGroup(@Param("doctorId") Integer doctorId, @Param("clientId") Integer clientId);
}
