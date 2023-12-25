package com.iotplatform.backend.service;

import com.iotplatform.backend.pojo.Result;

public interface IDatastreamlinkService {
    //关联设备上下通道
    public Result save(String deviceId, String title, Integer data_type);
    //根据向上通道ID查找向下通道
    public Result findDownByUp(String updatastream_id);
}
