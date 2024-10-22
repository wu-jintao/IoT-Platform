package com.iotplatform.backend.utils.MailUtil;

import java.util.Arrays;
import java.util.Properties;

/**
 * @ Created by zj  on 2017/7/12 at 17:38  for hiot.
 * @ Description:
 */
//记录发送邮件所需各种信息
public class MailSenderInfo {
    // 发送邮件的服务器的IP(或主机地址)
    private String mailServerHost;
    // 发送邮件的服务器的端口
    private String mailServerPort = "25";
    // 发件人邮箱地址
    private String fromAddress;
    // 收件人邮箱地址
    private String toAddress;
    // 登陆邮件发送服务器的用户名
    private String userName;
    // 登陆邮件发送服务器的密码
    private String password;
    // 是否需要身份验证
    private boolean validate = false;
    // 邮件主题
    private String subject;
    // 邮件的文本内容
    private String content;
    // 邮件附件的文件名
    private String[] attachFileNames;
    //过时时间
    private long outDate;
    //链接中的密钥
    private String validataCode;

    public Properties getProperties() {
        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.163.com");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        return prop;
    }

    //get、set方法
    public String getMailServerHost() {
        return mailServerHost;
    }

    public void setMailServerHost(String mailServerHost) {
        this.mailServerHost = mailServerHost;
    }

    public String getMailServerPort() {
        return mailServerPort;
    }

    public void setMailServerPort(String mailServerPort) {
        this.mailServerPort = mailServerPort;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getAttachFileNames() {
        return attachFileNames;
    }

    public void setAttachFileNames(String[] attachFileNames) {
        this.attachFileNames = attachFileNames;
    }

    public long getOutDate() {
        return outDate;
    }

    public void setOutDate(long outDate) {
        this.outDate = outDate;
    }

    public String getValidataCode() {
        return validataCode;
    }

    public void setValidataCode(String validataCode) {
        this.validataCode = validataCode;
    }

    @Override
    public String toString() {
        return "MailSenderInfo{" +
                "mailServerHost='" + mailServerHost + '\'' +
                ", mailServerPort='" + mailServerPort + '\'' +
                ", fromAddress='" + fromAddress + '\'' +
                ", toAddress='" + toAddress + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", validate=" + validate +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", attachFileNames=" + Arrays.toString(attachFileNames) +
                ", outDate=" + outDate +
                ", validataCode='" + validataCode + '\'' +
                '}';
    }
}
