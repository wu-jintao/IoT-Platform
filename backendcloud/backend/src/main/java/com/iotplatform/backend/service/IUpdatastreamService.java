package com.iotplatform.backend.service;

import com.iotplatform.backend.pojo.Result;
import com.iotplatform.backend.pojo.Updatastream;

public interface IUpdatastreamService {
    /*新增向上通道*/
    public Result save(String deviceId, Updatastream updatastream);
    /*删除向上通道*/
    public Result delete(String id);

    //根据ID查询设备下的所有通道并加入mqtt订阅
    public boolean findByDevIdAndAddSub(String deviceId);
    //查询设备下的所有通道并并从mqtt订阅主题中取消
    public boolean findByDevIdAndRemSub(String deviceId);
}