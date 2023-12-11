package com.iotplatform.backend.controller;

import com.iotplatform.backend.pojo.Device;
import com.iotplatform.backend.pojo.Result;
import com.iotplatform.backend.pojo.User;
import com.iotplatform.backend.service.DeviceService;
import com.iotplatform.backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @PostMapping("/register")
    public Result add(@RequestBody Device device){
        //记录日志
        log.info("UserController:新增设备, device:{}",device);
        //调用业务层新增功能
        try {
            return deviceService.addDevice(device,device.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("DEVICE_SAVE_ERROR");
        }
    }

    @PostMapping("/update")
    public Result update(@RequestBody Device device){
        //记录日志
        log.info("UserController:修改设备信息, device:{}",device);
        try{
            return deviceService.updateDevice(device);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("DEVICE_UPDATE_ERROR");
        }
    }
}