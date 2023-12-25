package com.iotplatform.backend.config;


/**
 * @ Created by liwenqiang on 2017/5/10 0010.
 * @ Description:
 */
public class Constants {
    /**
     * 存储当前登录用户id的字段名
     */
    public static final String CURRENT_USER_ID = "CURRENT_USER_ID";
    /**
     * 存储当前设备id的字段名
     */
    public static final String CURRENT_DEVICE_ID = "CURRENT_DEVICE_ID";
    /**
     * 存放Authorization的header字段
     */
    public static final String AUTHORIZATION = "Authorization";
    /**
     * Authorization(token)的类型:用户类型
     */
    public static final String AUTHORIZATION_USER = "use";
    /**
     * Authorization(token)的类型:设备类型
     */
    public static final String AUTHORIZATION_DEVICE = "dev";
    /**
     * 用户token有效期（小时）
     */
    public static final int TOKEN_EXPIRES_HOUR = 2160; //90天
    /**
     * 设备token有效期（天）
     */
    public static final int TOKEN_EXPIRES_DAY = 180; //180天

    /**
     * 用户文件存储目录：user
     */
    public static final String UPLOAD_PATH_USER = "user";
    /**
     * 图片加载目录(保存到数据库的相对路径(去除项目名))：
     */
    public static final String DOWNLOAD_PATH_IMG = "uploadfiles/images";
    /**
     * 图片加载目录(保存到数据库的相对路径(去除项目名))：
     */
    public static final String DOWNLOAD_PATH_IMG_DEFAULT = "/uploadfiles/images/default/nophoto.jpg";
    /**
     * 上传文件存储的根路径
     * 在 UNIX 系统上，路径的连接符的值为 '/'；在Windows系统上，它为 '\\';
     * File.separator可以根据不同的操作系统自动添加目录斜杠
     */
    public static final String UPLOAD_PATH = "D:/lk/lkiot_cloud/uploadfiles";
            //"D:\\huatec\\hiot_cloud\\uploadfiles";
//    PropertyConfigurer.getProperty("file.upload.path")
    /**
     * 图片存储文件夹
     */
    public static final String UPLOAD_PATH_IMG= "images";
    /**登录端识别码*/
    /**普通用户,移动端app*/
    public static final String LOGIN_CODE_APP="app";
    /**普通用户,网页端web*/
    public static final String LOGIN_CODE_WEB="web";
    /**
     * 时间格式：yyyy-MM-dd HH:mm:ss
     */
    public static final String TIME_FORMAT_TO_SEC = "yyyy-MM-dd HH:mm:ss";
    /**
     * 时间格式：yyyy-MM-dd
     */
    public static final String TIME_FORMAT_TO_DAY = "yyyy-MM-dd";
    /**
     * 时间格式：yyyy-MM-dd HH:mm
     */
    public static final String TIME_FORMAT_TO_MIN = "yyyy-MM-dd HH:mm";
    /**
     * 通道方向：向下
     */
    public static final Integer CHANNEL_DERICTION_DOWN = 2;
    /**
     * 通道方向：向上
     */
    public static final Integer CHANNEL_DERICTION_UP = 1;
    /**
     * 设备文件存储目录：device
     */
    public static final String UPLOAD_PATH_DEVICE = "device";
    /**
     * 分页查询默认一页显示条数：
     */
    public static final Integer PAGE_SIZE= 10;
}
