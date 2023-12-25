package com.iotplatform.backend.dao;

import com.iotplatform.backend.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Insert("insert into users (id,username,email,password,is_developer,is_staff,is_active,is_superuser,date_joined,lastlogin,phone,image) " +
            "values (#{id},#{username},#{email},#{password},#{isDeveloper},#{isStaff},#{isActive},#{isSuperuser},#{dateJoined},#{lastLogin},#{phone},#{img});")
    int saveForRegister(User user);  //保存用户注册信息

    /*登录验证，查询用户名是否存在*/
    @Select("select username from users where username = #{username}")
    String findUsername(String username);

    /*登录验证，查询邮箱是否存在*/
    @Select("select email from users where email = #{email}")
    String findEmail(String email);

    /*查询出登录用户所有信息*/
    @Select("select * from users where username = #{username} OR email = #{username}")
    User findForLogin(String username);

    /*修改密码*/
    @Update("update users set password = #{password} where id = #{id}")
    //public int updatePassword(String id,String password);
    void updatePassword(@Param("id") String id, @Param("password") String password);
}