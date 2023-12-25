package com.iotplatform.backend.controller;

import com.iotplatform.backend.pojo.Device;
import com.iotplatform.backend.pojo.Result;
import com.iotplatform.backend.pojo.User;
import com.iotplatform.backend.service.DeviceService;
import com.iotplatform.backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping(value="{id}")
    public Result delete(@PathVariable String id){
        try{
            return deviceService.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("DEVICE_DELETE_ERROR");
        }
    }

    /**查询所有设备通道及根据不同条件查询设备通道
     * direct：<1 设备向上通道 2 设备向下通道 0 设备所有通道>
     * data_type：<0：默认全部 1：数值2：开关 3 GPS 4：文本>**/
    @GetMapping("/{device_pk}/datastream/{direct}/{data_type}")
    public Result findDataStreamByType(@PathVariable("device_pk") String
                                               device_pk,@PathVariable("direct") Integer direct,@PathVariable("data_type")
                                       Integer data_type){
        try{
            return
                    deviceService.findDataStreamByType(device_pk,direct,data_type);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("SELECT_ERROR");
        }
    }
}