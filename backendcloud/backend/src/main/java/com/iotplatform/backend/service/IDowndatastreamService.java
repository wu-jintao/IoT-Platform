package com.iotplatform.backend.service;

import com.iotplatform.backend.pojo.Downdatastream;
import com.iotplatform.backend.pojo.Result;

public interface IDowndatastreamService {
    public Result save(String device_id, Downdatastream downdatastream);//增加
    public Result delete(String id);//删除
}
