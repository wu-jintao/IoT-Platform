package com.iotplatform.backend.utils.MailUtil;

/**
 * @ Created by zj  on 2017/7/12 at 17:40  for hiot.
 * @ Description:
 */
public class SendMailDemo {
    /**
     * 发送邮件
     * @param pwd 动态生成的新密码
     * @param email 邮箱地址
     */
    public static void sendEmail(String pwd,String email) {
      /*  try {
            //获取mail.properties中的内容
            Properties prop=new Properties();
            InputStream is=SendMailDemo.class.getClassLoader().getResourceAsStream("mail.properties");
            prop.load(is);
            String host=prop.getProperty("host");
            String port=prop.getProperty("port");
            String username=prop.getProperty("username");
            String password=prop.getProperty("password");
            String fromAddress=prop.getProperty("from");
            //实例化“发送邮件所需各种信息”
            MailSenderInfo mailInfo = new MailSenderInfo();
            mailInfo.setMailServerHost(host);
            mailInfo.setMailServerPort(port);
            mailInfo.setValidate(true);
            mailInfo.setUserName(username);
            mailInfo.setPassword(password);
            mailInfo.setFromAddress(fromAddress);
            mailInfo.setToAddress(email);
            mailInfo.setSubject("物联网【找回密码】");
            StringBuffer buffer = new StringBuffer();
            buffer.append("请勿回复本邮件.点击下面的链接,重设密码<br/><a href= "+resetPassHref+"target='_BLANK'>点击我重新设置密码</a>"+
                    "<br/>tips:本邮件超过30分钟,链接将会失效，需要重新申请'找回密码'" + key + "\t" + model);
            mailInfo.setContent(buffer.toString());
            // 发送邮件
            MailSender sms = new MailSender();
            sms.sendTextMail(mailInfo);
            System.out.println("邮件发送完毕");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("加载配置文件失败"+e);
        }
    }
    *//*public static void main(String[] args) {
        // 设置邮件服务器信息
        MailSenderInfo mailInfo = new MailSenderInfo();
        mailInfo.setMailServerHost("smtp.163.com");
        mailInfo.setMailServerPort("25");
        mailInfo.setValidate(true);
        // 邮箱用户名
        mailInfo.setUserName("hs_sygl_cpb@163.com");
        // 邮箱密码
        mailInfo.setPassword("HSsyglcpb123");
        // 发件人邮箱
        mailInfo.setFromAddress("hs_sygl_cpb@163.com");
        // 收件人邮箱
        mailInfo.setToAddress("xx@qq.com");
        // 邮件标题
        mailInfo.setSubject("发送邮件测试");
        // 邮件内容
        StringBuffer buffer = new StringBuffer();
        buffer.append("发送邮件测试");
        mailInfo.setContent(buffer.toString());
        // 发送邮件
        MailSender sms = new MailSender();
        // 发送文体格式
        sms.sendTextMail(mailInfo);
        // 发送html格式
//	   SimpleMailSender.sendHtmlMail(mailInfo);
        System.out.println("邮件发送完毕");
    }*//*
    *//**
         * @Author: zj
         * @Date: 2017/7/13 10:45
         * @Description: 生成邮箱链接
         * @Params：
         *//*
    public String createLink(MailSenderInfo stu){
        //生成密钥
        String secretKey= MD5Util.randomCreator(32);
        //设置过期时间
        Date outDate = new Date(System.currentTimeMillis() + 30 * 60 * 1000);// 30分钟后过期
        System.out.println(System.currentTimeMillis());
        long date = outDate.getTime() / 1000 * 1000;// 忽略毫秒数  mySql 取出时间是忽略毫秒数的
        //此处应该更新Studentinfo表中的过期时间、密钥信息
        stu.setOutDate(date);
        stu.setValidataCode(secretKey);
        studentinfoService.updateStudentinfo(stu);
        //将用户名、过期时间、密钥生成链接密钥
        String key =stu.getUserName() + "$" + date + "$" + secretKey;

        String digitalSignature = MD5Util.getMD5Str(key);// 数字签名

        HttpServletRequest request= Servlet.getRequest();

        String path=request.getContextPath();

        String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;

        String resetPassHref = basePath + "/toFindPassword3.action?sid="+ digitalSignature +"&id="+stu.getId();

        String emailContent = "请勿回复本邮件.点击下面的链接,重设密码,本邮件超过30分钟,链接将会失效，需要重新申请找回密码." + resetPassHref;

        return emailContent;
    }
*/
    }
}

