package com.iotplatform.backend.utils.MailUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @ Created by zj  on 2017/7/14 at 16:41  for hiot.
 * @ Description:
 */
public class MSUtil {
    public static void sendEmail(String password, String email) throws IOException {
        //获取mail.properties中的内容
        Properties prop = new Properties();
        InputStream is = MSUtil.class.getClassLoader().getResourceAsStream("mail.properties");
        prop.load(is);
        String host = prop.getProperty("host");
        String port = prop.getProperty("port");
        String username = prop.getProperty("username");
        String password1 = prop.getProperty("password");
        String fromAddress = prop.getProperty("from");
        //实例化“发送邮件所需各种信息”
        MailSenderInfo mailInfo = new MailSenderInfo();
        mailInfo.setMailServerHost(host);
        mailInfo.setMailServerPort(port);
        mailInfo.setValidate(true);
        mailInfo.setUserName(username);
        mailInfo.setPassword(password1);
        mailInfo.setFromAddress(fromAddress);
        mailInfo.setToAddress(email);
        mailInfo.setSubject("华晟物联云平台【动态密码】");
        StringBuffer buffer = new StringBuffer();
        buffer.append("您的动态密码为： " + password + ",请使用此密码登录系统，更改此密码请到个人中心");
        mailInfo.setContent(buffer.toString());
        // 发送邮件
        MailSender sms = new MailSender();
        sms.sendTextMail(mailInfo);
        System.out.println("邮件发送完毕");
    }

   /* public static void main(String[] args) throws Exception {
        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.sohu.com");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        //使用JavaMail发送邮件的5个步骤
        //1、创建session
        Session session = Session.getInstance(prop);
        //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);
        //2、通过session得到transport对象
        Transport ts = session.getTransport();
        //3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
        ts.connect("smtp.sohu.com", "hiot_cloud", "hiotcloud123");
        //4、创建邮件
        javax.mail.Message message = createSimpleMail(session);
        //5、发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
        System.out.print("发送成功");
    }
    public static MimeMessage createSimpleMail(Session session)
           throws Exception {
              //创建邮件对象
              MimeMessage message = new MimeMessage(session);
               //指明邮件的发件人
              message.setFrom(new InternetAddress("hiot_cloud@sohu.com"));
             //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
                message.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress("1097566223@qq.com"));
               //邮件的标题
               message.setSubject("只包含文本的简单邮件");
               //邮件的文本内容
                message.setContent("你好啊！", "text/html;charset=UTF-8");
               //返回创建好的邮件对象
                return message;
          }*/
}
