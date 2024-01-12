package com.iotplatform.backend.service.ext.impl;

import com.iotplatform.backend.dao.DeviceMapper;
import com.iotplatform.backend.dao.UpdatastreamDao;
import com.iotplatform.backend.mqtt.service.ICoreServiceOuter;
import com.iotplatform.backend.service.ext.IMqttService;
import com.lk.utils.CollectionUtil;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Created by liwenqiang  on 2017/10/23 0023 at 上午 10:36  for hiot
 * @ Description:
 */

@Service
public class MqttServiceImpl implements IMqttService {
    @Autowired
    private ICoreServiceOuter coreServiceOuter;
    @Autowired
    private UpdatastreamDao updatastreamDao;
    @Autowired
    private DeviceMapper deviceDao;

//    @Autowired
//    private DatastreamDao datastreamDao;

    @Override
    public boolean addSub(String[] topics,int qos) throws MqttException {
        if(topics != null && topics.length>0){
            return coreServiceOuter.addSub(topics,qos);
        }
        return true;
    }
    @Override
    public boolean removeSub(String[] topics) throws MqttException {
        if(topics!=null && topics.length>0){
            return coreServiceOuter.removeSub(topics);
        }
        return true;
    }

    @Override
    public boolean addPub(String topic, int qos, String msg) throws MqttException {
        return coreServiceOuter.addPub(topic,qos,msg);
    }
    @Override
    public void initSubMqtt()throws MqttException{
        /** 1、查询出所有设备ID并加入到MQTT主题订阅中，用户判断设备是否连接及是否掉线
         ** 2、查询出所有向上通道并加入到MQTT主题订阅中,用于接收设备的数据
         **/

        List<String> updatastreamIds = updatastreamDao.findAllIdByActivatedDevice();
        if(updatastreamIds==null){
            updatastreamIds = new ArrayList<>();
        }
        List<String> deviceIds = deviceDao.findAllId();
        if(!CollectionUtil.ListIsEmpty(deviceIds)){
            updatastreamIds.addAll(deviceIds);
        }
        if(!CollectionUtil.ListIsEmpty(updatastreamIds)){
            String[] topics = new String[updatastreamIds.size()];
            updatastreamIds.toArray(topics);
            coreServiceOuter.addSub(topics,0);//所有的设备ID和通道ID都作为topic在初始化订阅的时候被订阅了
        }
    }

//    @Override
//    public Integer findDataTypeByUdsId(String topic) {
////        return updatastreamDao.findDataTypeById(topic);
//        return datastreamDao.findDataTypeById(topic);
//    }

}
