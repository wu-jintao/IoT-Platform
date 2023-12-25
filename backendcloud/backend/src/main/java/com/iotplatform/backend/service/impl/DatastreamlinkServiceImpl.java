package com.iotplatform.backend.service.impl;

import com.iotplatform.backend.dao.DatastreamlinkDao;
import com.iotplatform.backend.dao.DeviceMapper;
import com.iotplatform.backend.dao.DowndatastreamDao;
import com.iotplatform.backend.dao.UpdatastreamDao;
import com.iotplatform.backend.pojo.*;
import com.iotplatform.backend.service.IDatastreamlinkService;
import com.iotplatform.backend.utils.StringUtil;
import com.iotplatform.backend.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class DatastreamlinkServiceImpl implements IDatastreamlinkService {
    @Autowired
    private DatastreamlinkDao datastreamlinkDao;
    @Autowired
    private DeviceMapper deviceDao;
    @Autowired
    private UpdatastreamDao updatastreamDao;
    @Autowired
    private DowndatastreamDao downdatastreamDao;
    @Override
    public Result save(String deviceId, String title, Integer data_type) {
        if( StringUtil.isEmpty(deviceId)){
            return Result.error("GET_ID_ERROR");
        }
        //模板是否存在
        Device device = deviceDao.findById(deviceId);
        if(device==null){
            return Result.error("DATA_NOT_FOUND");
        }
        //保存向上通道
        Updatastream updatastream = new Updatastream();
        updatastream.setId(UUIDUtil.getUUID());
        updatastream.setTitle(title);
        updatastream.setDataType(data_type);
        updatastream.setDevice(device);
        updatastreamDao.save(updatastream);
        //保存向下通道
        Downdatastream downdatastream = new Downdatastream();
        downdatastream.setId(UUIDUtil.getUUID());
        downdatastream.setTitle(title);
        downdatastream.setDataType(data_type);
        downdatastream.setDevice(device);
        downdatastreamDao.save(downdatastream);
        //关联上下通道
        Datastreamlink datastreamlink = new Datastreamlink();
        datastreamlink.setId(UUIDUtil.getUUID());
        datastreamlink.setTitle(title);
        datastreamlink.setUpdatastream(updatastream);
        datastreamlink.setDowndatastream(downdatastream);
        datastreamlinkDao.save(datastreamlink);
        return Result.success();
    }
    @Override
    public Result findDownByUp(String updatastream_id) {
        String downdatastream_id =
                datastreamlinkDao.findDownByUp(updatastream_id);
        if(downdatastream_id != null){
            Downdatastream downdatastream =
                    downdatastreamDao.findById(downdatastream_id);
            return Result.success(downdatastream);
        }else {
            return Result.success("SELECT_EMPTY");
        }
    }
}
