package com.iotplatform.backend.controller;

import com.iotplatform.backend.pojo.Result;
import com.iotplatform.backend.pojo.User;
import com.iotplatform.backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result save(@RequestBody User user){
        //注册校验
        if (null != user.getUsername() && null != user.getPassword() && null != user.getEmail()){
            if (null != userService.findUsername(user.getUsername()) && !"".equals(userService.findUsername(user.getUsername()))){
                return Result.error("用户名已经被注册");
            }else{
                Boolean ls = userService.checkEmail(user.getEmail());
                if (null != userService.findEmail(user.getEmail()) && !"".equals(userService.findEmail(user.getEmail()))) {
                    return Result.error("邮箱已经被注册");
                }else if (!ls) {
                    return Result.error("邮箱格式错误");
                }else {
                    try {
                        return userService.addUser(user);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return Result.error("注册用户失败");
                    }
                }
            }
        }
        return Result.error("用户名、密码和邮箱不能为空！！！");
    }

    @PutMapping("/reset_password/{email}/{username}")
    public Result forgetPassword(
            @PathVariable String email,
            @PathVariable String username) {
        try {
            return userService.resetPassword(email, username);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("密码更新错误");
        }
    }
}