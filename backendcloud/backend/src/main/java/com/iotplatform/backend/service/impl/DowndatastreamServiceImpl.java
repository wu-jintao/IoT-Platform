package com.iotplatform.backend.service.impl;

import com.iotplatform.backend.dao.DatastreamlinkDao;
import com.iotplatform.backend.dao.DowndatastreamDao;
import com.iotplatform.backend.pojo.Device;
import com.iotplatform.backend.pojo.Downdatastream;
import com.iotplatform.backend.pojo.Result;
import com.iotplatform.backend.service.IDowndatastreamService;
import com.iotplatform.backend.utils.StringUtil;
import com.iotplatform.backend.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class DowndatastreamServiceImpl implements IDowndatastreamService {
    @Autowired
    private DowndatastreamDao downdatastreamDao;
    @Autowired
    private DatastreamlinkDao datastreamlinkDao;
    @Override
    public Result save(String device_id, Downdatastream downdatastream) {
        if( StringUtil.isEmpty(device_id)){
            return Result.error("GET_ID_ERROR");
        }
        Device device = new Device();
        device.setId(device_id);
        downdatastream.setDevice(device);
        downdatastream.setId(UUIDUtil.getUUID());
        downdatastreamDao.save(downdatastream);
        return Result.success(downdatastream);
    }
    @Override
    public Result delete(String id) {
        if (StringUtil.isEmpty(id)) {
            return Result.error("GET_ID_ERROR");
        }
        datastreamlinkDao.deleteByDowndatastreamId(id);
        downdatastreamDao.delete(id);
        return Result.success();
    }
}
