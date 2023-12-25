package com.iotplatform.backend.service;

import com.iotplatform.backend.pojo.Result;
import com.iotplatform.backend.pojo.User;


public interface UserService {
    Result addUser(User user);
    /*登录验证，查询用户名是否存在*/
    String findUsername(String username);
    /*登录验证，查询邮箱是否存在*/
    String findEmail(String email);
    boolean checkEmail(String emaile);
    boolean isPassword(String password);
    Result resetPassword(String email, String username);
    User findForLogin(String username);
    String getStringRandom(int length);
}
