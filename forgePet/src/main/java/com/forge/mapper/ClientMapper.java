package com.forge.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.forge.vo.NameVo;
import com.forge.entity.Client;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author MixJade
 * @since 2022-12-22
 */
@Mapper
public interface ClientMapper extends BaseMapper<Client> {
    boolean deleteId(@Param("delDate") String delDate, @Param("clientId") long clientId);

    boolean deleteIdGroup(@Param("delDate") String delDate, @Param("idGroup") long[] idGroup);

    int selectClientCount(@Param("clientName") String clientName);

    List<Client> selectClientPage(@Param("clientName") String clientName, @Param("begin") int begin, @Param("pageItem") int pageItem);

    @Select("SELECT client_id as roleId, client_name as roleName FROM client WHERE is_del = '0'")
    List<NameVo> selectName();

    @Select("select count(*) from client where is_del=0 and client_username=#{username} and client_tel=#{mail}")
    int sureMail(@Param("username") String username, @Param("mail") String mail);

    @Select("select count(*) from client where is_del=0 and client_username=#{username}")
    int isExist(@Param("username") String username);
}
