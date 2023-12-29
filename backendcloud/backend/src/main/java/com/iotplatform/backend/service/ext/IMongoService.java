package com.iotplatform.backend.service.ext;

import com.iotplatform.mongo.entity.base.UpdatastreamData;
import org.springframework.stereotype.Component;

@Component
public interface IMongoService {
    /**根据向上通道ID查询通道数据类型*/
    Integer findDataTypeByUdsId(String updatastreamId);
    /**封装向上通道数据对象，用以调用mongo模块保存数据到MongoDB*/
    UpdatastreamData getUdsData(String updatastreamId, Integer dataType, String deviceDataStr);
}