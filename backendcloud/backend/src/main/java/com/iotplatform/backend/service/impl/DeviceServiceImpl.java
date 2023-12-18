package com.iotplatform.backend.service.impl;

import com.iotplatform.backend.dao.DeviceMapper;
import com.iotplatform.backend.pojo.Device;
import com.iotplatform.backend.pojo.Result;
import com.iotplatform.backend.service.DeviceService;
import com.iotplatform.backend.utils.CollectionUtil;
import com.iotplatform.backend.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;


    @Override
    public Result addDevice(Device device, String userId) {
        /**判断此用户下设备名称是否重复*/
        List<Device > devices= deviceMapper.findDevicesByUserId(userId);
        if (!CollectionUtil.ListIsEmpty(devices)) {
            for (Device d : devices) {
                if (device .getTitle().equals(d.getTitle())) {
                    return Result.error("设备已经存在！");
                }
            }
        }
        if (null != userId) {
//            User user = userMapper.findById(userId);
            device.setUserId(userId);
        }
        device.setId(UUIDUtil.getUUID());
        device.setCreated(LocalDateTime.now());
        device.setUpdated(LocalDateTime.now());
        device.setDevStatus(0);
        deviceMapper.add(device);
        return Result.success(device);
    }

    @Override
    public Result updateDevice(Device device){
        if (device.getId() == null || "".equals(device.getId())) {
            return Result.error("DEVICE_NOT_FOUND");
        }
        device.setUpdated(LocalDateTime.now());
        deviceMapper.update(device);
        return Result.success(device);

    }
}