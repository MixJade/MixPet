package com.ship.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ship.model.dto.MsgDo;
import com.ship.model.dto.MsgNameDo;
import com.ship.model.dto.NameDo;
import com.ship.model.entity.Msg;
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
    NameDo clientGetNearDoctor(@Param("clientId") Integer clientId);

    /**
     * 查询特定用户与医生对话
     */
    List<MsgDo> clientGetADoctor(@Param("clientId") Integer clientId, @Param("doctorId") Integer doctorId);

    /**
     * 查询与用户有过联系的医生，这里使用全参构造接收结果，不必指定字段名
     */
    List<MsgNameDo> clientGetDoctorGroup(Integer clientId, Integer doctorId);

    /**
     * 查询医生最近谈话的用户
     */
    NameDo doctorGetNearClient(@Param("doctorId") Integer doctorId);

    /**
     * 查询特定医生与用户对话
     */
    List<MsgDo> doctorGetAClient(@Param("doctorId") Integer doctorId, @Param("clientId") Integer clientId);

    /**
     * 医生谈过话的用户
     */
    List<MsgNameDo> doctorGetClientGroup(@Param("doctorId") Integer doctorId, @Param("clientId") Integer clientId);
}
