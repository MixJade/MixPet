package com.ship.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ship.util.PageUtil;
import com.ship.vo.NameVo;
import com.ship.entity.Client;
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
    int selectClientCount(@Param("clientName") String clientName);

    List<Client> selectClientPage(@Param("clientName") String clientName, @Param("pu") PageUtil pu);

    @Select("SELECT client_id as roleId, client_name as roleName FROM client WHERE is_del = '0'")
    List<NameVo> selectName();

    /**
     * 登录控制器：密码找回时确定邮箱是否与账号匹配
     *
     * @param username 账号
     * @param mail     邮箱
     * @return 数据条数
     */
    @Select("select count(*) from client where is_del=0 and client_username=#{username} and client_tel=#{mail}")
    int sureMail(@Param("username") String username, @Param("mail") String mail);

    /**
     * 登录控制器：注册时确定当前用户名独一无二
     *
     * @param username 用户名
     * @return 数据条数
     */
    @Select("select count(*) from client where is_del=0 and client_username=#{username}")
    int isExist(@Param("username") String username);
}
