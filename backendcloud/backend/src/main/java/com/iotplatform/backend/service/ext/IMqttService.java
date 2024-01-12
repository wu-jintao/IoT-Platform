package com.iotplatform.backend.service.ext;

import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * @ Created by liwenqiang  on 2017/10/23 0023 at 上午 10:35  for hiot
 * @ Description: MQTT发布订阅统一接口
 */
public interface IMqttService {
    /** 通过mqtt订阅主题实现对设备数据的上传保存*/
    public boolean addSub(String[] topics, int qos)throws MqttException;
    /** 取消mqtt订阅主题*/
    public boolean removeSub(String[] topics)throws MqttException;

    /** 通过mqtt发布主题实现对设备控制*/
    public boolean addPub(String topic, int qos, String msg)throws MqttException;

    /** 初始化订阅，用于程序初始化和mqtt-subclient掉线重连重新订阅*/
    public void initSubMqtt()throws MqttException;

//    /** 判断主题是否是通道ID及通道数据类型*/
//    public Integer findDataTypeByUdsId(String topic);
}
