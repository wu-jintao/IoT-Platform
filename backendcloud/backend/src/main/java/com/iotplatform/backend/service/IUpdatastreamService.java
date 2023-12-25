package com.iotplatform.backend.service;

import com.iotplatform.backend.pojo.Result;
import com.iotplatform.backend.pojo.Updatastream;

public interface IUpdatastreamService {
    /*新增向上通道*/
    public Result save(String deviceId, Updatastream updatastream);
    /*删除向上通道*/
    public Result delete(String id);
}