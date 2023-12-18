package com.huatec.hiot_cloud.mongodb.service;

import com.huatec.hiot_cloud.mongodb.entity.Alert;
import com.huatec.hiot_cloud.mongodb.entity.Measurement;

import java.util.Date;
import java.util.List;

/**
 * @ Created by liwenqiang  on 2017/12/23 0023 at 下午 2:53  for mongo_integration
 * @ Description:
 */
public interface IMeasurementService {
    /**根据向上通道ID保存设备数据*/
    public void insert(Measurement measurement);
    /**根据向上通道ID查询最近一条数据*/
    public Measurement findLatestOneByUdsId(String updatastreamId);
    /**根据向上通道ID查询指定位置指定数量的数据*/
    public List<Measurement> findListByUdsId(String updatastreamId,int skip,int limit);
    /**根据向上通道ID查询指定起讫时间点的数据*/
    public List<Measurement> findListByUdsIdWithPeriod(String updatastreamId,Date begin,Date end);
}
