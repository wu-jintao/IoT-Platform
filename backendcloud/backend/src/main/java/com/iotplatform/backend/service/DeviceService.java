package com.iotplatform.backend.service;

import com.iotplatform.backend.pojo.Device;
import com.iotplatform.backend.pojo.Result;

public interface DeviceService {
    // 新建设备
    Result addDevice(Device device, String userId);

    Result updateDevice(Device device);

    Result deleteById(String id);

    Result findDataStreamByType(String tempId, Integer direct, Integer dataType);
}