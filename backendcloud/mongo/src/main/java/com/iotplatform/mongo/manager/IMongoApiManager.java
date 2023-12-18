package com.huatec.hiot_cloud.mongodb.manager;

import com.huatec.hiot_cloud.mongodb.entity.base.UpdatastreamData;

import java.util.Date;
import java.util.List;

/**
 * @ Created by liwenqiang  on 2017/7/20 0020 at 下午 2:16  for hiot
 * @ Description:
 */
public interface IMongoApiManager {
    /**上传设备数据保存到mongodb*/
    public void upload(int dataType, UpdatastreamData updatastreamData);
    /**查询某一通道最近一条数据*/
    public UpdatastreamData downloadLastedOneByUdsId(Integer dataType, String updatastreamId);
    /**查询某一通道指定位置指定数量数据*/
    public List<? extends UpdatastreamData> downloadListByUdsId(Integer dataType, String updatastreamId, Integer skip, Integer limit);
    /**查询某一通道指定时间段数据*/
    public List<? extends UpdatastreamData> downloadListByUdsIdWithPeriod(Integer dataType, String updatastreamId, Date begin, Date end);
}
