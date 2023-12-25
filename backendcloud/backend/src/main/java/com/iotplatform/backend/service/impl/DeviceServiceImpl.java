package com.iotplatform.backend.service.impl;

import com.iotplatform.backend.config.Constants;
import com.iotplatform.backend.dao.DeviceMapper;
import com.iotplatform.backend.dao.DowndatastreamDao;
import com.iotplatform.backend.dao.UpdatastreamDao;
import com.iotplatform.backend.dto.DatastreamDto;
import com.iotplatform.backend.pojo.Device;
import com.iotplatform.backend.pojo.Downdatastream;
import com.iotplatform.backend.pojo.Result;
import com.iotplatform.backend.pojo.Updatastream;
import com.iotplatform.backend.service.DeviceService;
import com.iotplatform.backend.service.IDatastreamlinkService;
import com.iotplatform.backend.service.IDowndatastreamService;
import com.iotplatform.backend.service.IUpdatastreamService;
import com.iotplatform.backend.utils.CollectionUtil;
import com.iotplatform.backend.utils.StringUtil;
import com.iotplatform.backend.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private UpdatastreamDao updatastreamDao;
    @Autowired
    private IUpdatastreamService updatastreamService;
    @Autowired
    private DowndatastreamDao downdatastreamDao;
    @Autowired
    private IDowndatastreamService downdatastreamService;
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

    @Override
    public Result deleteById(String id) {
        Device device = deviceMapper.findById(id);
        if (device == null) {
            return Result.error("DEVICE_NOT_FOUND");
        }
        List<Updatastream> updatastreams = updatastreamDao.findByDevId(id);
        for (Updatastream up : updatastreams) {
            updatastreamService.delete(up.getId());
        }
        List<Downdatastream> downdatastreams =
                downdatastreamDao.findByDeviceId(id);
        for (Downdatastream down : downdatastreams
        ) {
            downdatastreamService.delete(down.getId());
        }
        deviceMapper.delete(id);
        return Result.success(null);
    }

    @Override
    public Result findDataStreamByType(String deviceId, Integer direct, Integer data_type) {
        if (StringUtil.isEmpty(deviceId)) {
            return Result.error("GET_DEVICE_ID_ERROR");
        }
        //整理通道方向
        if (direct == null || (direct != 0 && direct != 1 && direct != 2)) {
            direct=0;
        }
        //整理数据类型
        if (data_type == null || (data_type != 1 && data_type != 2 && data_type
                != 3 && data_type != 4)) {
            data_type = 0;
        }
        //重新封装数据（增加通道方向标识）
        //模板的所有通道
        List allupdataStreams = new ArrayList();
        if(direct==0||direct == Constants.CHANNEL_DERICTION_UP){
            //模板的所有向上通道
            List<Updatastream> updatastreams = updatastreamDao.findByType(deviceId, data_type);
            for (Updatastream up : updatastreams) {
                //向上通道封装类。
                DatastreamDto datastreamDto = new DatastreamDto();
                datastreamDto.setId(up.getId());
                datastreamDto.setTitle(up.getTitle());
                datastreamDto.setData_type(up.getDataType());
                datastreamDto.setDirection(Constants.CHANNEL_DERICTION_UP);
                datastreamDto.setUnit(up.getUnit());
                datastreamDto.setMark(up.getMark());
                datastreamDto.setDevice(up.getDevice());
                //上下通道统一放入LIST
                allupdataStreams.add(datastreamDto);
            }
            if(direct == Constants.CHANNEL_DERICTION_UP){
                return Result.success(allupdataStreams);
            }
        }
        //模板的所有向下通道
        if(direct==0||direct== Constants.CHANNEL_DERICTION_DOWN){
            List<Downdatastream> downdatastreams =
                    downdatastreamDao.findByType(deviceId, data_type);
            for (Downdatastream down : downdatastreams) {
                //向下通道封装类。
                DatastreamDto datastreamDto = new DatastreamDto();
                datastreamDto.setId(down.getId());
                datastreamDto.setTitle(down.getTitle());
                datastreamDto.setData_type(down.getDataType());
                datastreamDto.setDirection(Constants.CHANNEL_DERICTION_DOWN);
                datastreamDto.setUnit(down.getUnit());
                datastreamDto.setMark(down.getMark());
                datastreamDto.setDevice(down.getDevice());
                //上下通道统一放入LIST
                allupdataStreams.add(datastreamDto);
            }
            if(direct== Constants.CHANNEL_DERICTION_DOWN){
                return Result.success(allupdataStreams);
            }
        }
        return Result.success(allupdataStreams);
    }
}