package com.iotplatform.backend.service;

import com.iotplatform.backend.pojo.Result;
import com.iotplatform.backend.pojo.User;


public interface UserService {
    public Result addUser(User user);
    /*登录验证，查询用户名是否存在*/
    public String findUsername(String username);
    /*登录验证，查询邮箱是否存在*/
    public String findEmail(String email);
    public boolean checkEmail(String emaile);
    public boolean isPassword(String password);
    public Result resetPassword(String email, String username);
    public User findForLogin(String username);
    public String getStringRandom(int length);
}
