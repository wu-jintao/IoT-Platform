package com.iotplatform.backend.service.impl;

import com.iotplatform.backend.dao.DatastreamlinkDao;
import com.iotplatform.backend.dao.UpdatastreamDao;
import com.iotplatform.backend.pojo.Device;
import com.iotplatform.backend.pojo.Result;
import com.iotplatform.backend.pojo.Updatastream;
import com.iotplatform.backend.service.IDowndatastreamService;
import com.iotplatform.backend.service.IUpdatastreamService;
import com.iotplatform.backend.utils.StringUtil;
import com.iotplatform.backend.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class UpdatastreamService implements IUpdatastreamService {
    @Autowired
    private UpdatastreamDao updatastreamDao;
    @Autowired
    private DatastreamlinkDao datastreamlinkDao;
    @Autowired
    private IDowndatastreamService downdatastreamService;
    @Override
    public Result save(String deviceId, Updatastream updatastream) {
        if (deviceId.isEmpty()) {
            return Result.error("GET_ID_ERROR");
        }
        Device device = new Device();
        device.setId(deviceId);
        updatastream.setDevice(device);
        updatastream.setId(UUIDUtil.getUUID());
        updatastreamDao.save(updatastream);
        return Result.success(updatastream);
    }
    @Override
    public Result delete(String id) {
        if (StringUtil.isEmpty(id)) {
            return Result.error("GET_ID_ERROR");
        }
        //根据向上通道找到向下通道并删除
        String downdatastreamId = datastreamlinkDao.findDownByUp(id);
        //先删双向通道关联，再删向下，向上通道
        datastreamlinkDao.deleteByUpdatastreamId(id);
        if (!StringUtil.isEmpty(downdatastreamId)) {
            downdatastreamService.delete(downdatastreamId);
        }
        updatastreamDao.deleteById(id);
        return Result.success("DELETE_SUCCESS");
    }
}