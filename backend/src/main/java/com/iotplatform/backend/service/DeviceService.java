package com.iotplatform.backend.service;

import com.iotplatform.backend.pojo.Device;
import com.iotplatform.backend.pojo.Result;

public interface DeviceService {
    // 新建设备
    public Result addDevice(Device device, String userId);

    public Result updateDevice(Device device);
}