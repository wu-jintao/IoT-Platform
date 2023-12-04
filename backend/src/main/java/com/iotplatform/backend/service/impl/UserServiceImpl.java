package com.iotplatform.backend.service.impl;

import com.iotplatform.backend.pojo.Result;
import com.iotplatform.backend.pojo.User;
import com.iotplatform.backend.service.UserService;
import com.iotplatform.backend.utils.MD5Util;
import com.iotplatform.backend.utils.MailUtil.MSUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.iotplatform.backend.dao.UserMapper;


@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public Result addUser(User user) {
        //密码加密
        if (isPassword(user.getPassword())) {
            user.setPassword(MD5Util.getMD5Str(user.getPassword()));
        } else {
            return Result.error("密码格式不正确");
        }
        //超级管理员不通过注册导入
        user.setIsSuperuser((short) 0);

        user.setDateJoined(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        //调用添加方法
        int saveResult = userMapper.saveForRegister(user);
        if (saveResult != 0) {
            //保存到数据库成功
            log.info("UserServiceImpl:新增用户, user:{}",user);
            return Result.success(user);
        } else {
            return Result.error("注册用户失败");
        }
    }

    @Override
    public String findUsername(String username) {
        return userMapper.findUsername(username);
    }

    @Override
    public String findEmail(String email) {
        return userMapper.findEmail(email);
    }

    /**
     * 正则表达式校验邮箱
     *
     * @param emaile 待匹配的邮箱
     * @return 匹配成功返回true 否则返回false;
     */
    @Override
    public boolean checkEmail(String emaile) {
        String RULE_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        //正则表达式的模式
        Pattern p = Pattern.compile(RULE_EMAIL);
        //正则表达式的匹配器
        Matcher m = p.matcher(emaile);
        //进行正则匹配
        return m.matches();
    }

    /**
     * 判断密码格式是否正确
     *
     * @param password 密码
     */
    @Override
    public boolean isPassword(String password) {
        //    String str = "^[0-9a-zA-Z]{6,16}+$";//必须同时包含字母数字并且是6-16位
        String str = "^[a-zA-Z]\\w{5,17}$";//密码字母数字下划线组成6-18位
        //正则表达式的模式
        Pattern p = Pattern.compile(str);
        //正则表达式的匹配器
        Matcher m = p.matcher(password);
        //进行正则匹配
        return m.matches();
    }

    @Override
    public Result resetPassword(String email, String username){
        User user = findForLogin(username);
        if (user == null || !user.getEmail().equals(email)) {
            return Result.error("用户名或者email错误");
        }
        //构建动态密码
        //int newPassword = new Random().nextInt(999999);
        String Password = getStringRandom(8);
        String newPassword = "A" + Password;
        System.out.print("newPassword" + newPassword);
        String md5_password = MD5Util.getMD5Str(newPassword);
        //更新密码
        userMapper.updatePassword(Integer.valueOf(user.getId()), md5_password);
        //向邮箱发送动态密码
        try {
            MSUtil.sendEmail(newPassword, email);
        } catch (Exception e) {
            e.printStackTrace();
//            throw ie;
            return Result.error("email发送错误");
        }
        return Result.success();
    }

    @Override
    public User findForLogin(String username) {
        return userMapper.findForLogin(username);
    }

    //生成随机数字和字母,
    @Override
    public String getStringRandom(int length) {

        String val = "";
        Random random = new Random();

        //参数length，表示生成几位随机数
        for (int i = 0; i < length; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (random.nextInt(26) + temp);
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }
}
