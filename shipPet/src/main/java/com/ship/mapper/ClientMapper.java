package com.ship.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ship.model.dto.NameDo;
import com.ship.model.entity.Client;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
    IPage<Client> selectClientPage(IPage<Client> page, String clientName);

    @Select("SELECT client_id as roleId, client_name as roleName FROM client WHERE is_del=0")
    List<NameDo> selectName();

    /**
     * 【子查询】名下有宠物的用户，带上当前用户
     *
     * @param clientId 当前客户ID
     * @return 客户名称+ID
     */
    List<NameDo> selectNameHavePet(Integer clientId);

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

    /**
     * 用户修改自己的资料
     *
     * @param client 用户信息
     * @return 修改成功
     */
    boolean updateSelfById(Client client);

    /**
     * 找回密码时，通过用户名修改密码
     *
     * @param password 修改之后的密码
     * @param username 用户名
     * @return 修改成功
     */
    @Update("UPDATE client SET client_password=#{password} WHERE client_username=#{username}")
    boolean updatePwdByUsername(String password, String username);
}
