package com.iotplatform.backend.utils.MailUtil;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * @ Created by zj  on 2017/7/12 at 17:39  for hiot.
 * @ Description:
 */
//邮箱用户名和邮箱证器
public class MyAuthenticator extends Authenticator {
    String userName = null;

    String email = null;

    public MyAuthenticator() {}
    public MyAuthenticator(String username, String email) {
        this.userName = username;
        this.email = email;
    }
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, email);
    }
}

