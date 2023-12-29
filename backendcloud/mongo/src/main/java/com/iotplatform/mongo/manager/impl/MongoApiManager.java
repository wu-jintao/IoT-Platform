package com.iotplatform.mongo.manager.impl;

import com.iotplatform.mongo.entity.Alert;
import com.iotplatform.mongo.entity.Measurement;
import com.iotplatform.mongo.entity.Status;
import com.iotplatform.mongo.entity.Waypoint;
import com.iotplatform.mongo.entity.base.UpdatastreamData;
import com.iotplatform.mongo.manager.IMongoApiManager;
import com.iotplatform.mongo.service.IAlertService;
import com.iotplatform.mongo.service.IMeasurementService;
import com.iotplatform.mongo.service.IStatusService;
import com.iotplatform.mongo.service.IWaypointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @ Created by liwenqiang  on 2017/5/19 0019 at 下午 12:26  for hiot
 * @ Description:  统一处理接口调用的对外访问公共类。
 */
@Component
public class MongoApiManager implements IMongoApiManager {
    @Autowired
    private IMeasurementService measurementService;
    @Autowired
    private IAlertService alertService;
    @Autowired
    private IStatusService statusService;
    @Autowired
    private IWaypointService waypointService;

    @Override
    public void upload(int dataType, UpdatastreamData updatastreamData) {
        switch (dataType) {
            //数值测量值型:Measurement
            case 1:
                measurementService.insert((Measurement) updatastreamData);
                break;
            //开关状态型:Status
            case 2:
                statusService.insert((Status) updatastreamData);
                break;
            //地理位置定位型:Waypoint
            case 3:
                waypointService.insert((Waypoint) updatastreamData);
                break;
            //文本预警消息型:Alert
            case 4:
                alertService.insert((Alert) updatastreamData);
                break;
            default:
                throw new IllegalArgumentException("数据类型码不正确");
        }
    }

    @Override
    public UpdatastreamData downloadLastedOneByUdsId(Integer dataType, String updatastreamId) {
        switch (dataType) {
            //数值测量值型:Measurement
            case 1:
                return measurementService.findLatestOneByUdsId(updatastreamId);
            //开关状态型:Status
            case 2:
                return statusService.findLatestOneByUdsId(updatastreamId);
            //地理位置定位型:Waypoint
            case 3:
                return waypointService.findLatestOneByUdsId(updatastreamId);
            //文本预警消息型:Alert
            case 4:
                return alertService.findLatestOneByUdsId(updatastreamId);
            default:
                throw new IllegalArgumentException("数据类型码不正确");
        }
    }

    @Override
    public List<? extends UpdatastreamData> downloadListByUdsId(Integer dataType, String updatastreamId, Integer skip, Integer limit) {
        switch (dataType) {
            //数值测量值型:Measurement
            case 1:
                return measurementService.findListByUdsId(updatastreamId, skip, limit);
            //开关状态型:Status
            case 2:
                return statusService.findListByUdsId(updatastreamId, skip, limit);
            //地理位置定位型:Waypoint
            case 3:
                return waypointService.findListByUdsId(updatastreamId, skip, limit);
            //文本预警消息型:Alert
            case 4:
                return alertService.findListByUdsId(updatastreamId, skip, limit);
            default:
                throw new IllegalArgumentException("通道数据类型码不正确");
        }
    }

    @Override
    public List<? extends UpdatastreamData> downloadListByUdsIdWithPeriod(Integer dataType, String updatastreamId, Date begin, Date end) {
        switch (dataType) {
            //数值测量值型:Measurement
            case 1:
                return measurementService.findListByUdsIdWithPeriod(updatastreamId, begin, end);
            //开关状态型:Status
            case 2:
                return statusService.findListByUdsIdWithPeriod(updatastreamId, begin, end);
            //地理位置定位型:Waypoint
            case 3:
                return waypointService.findListByUdsIdWithPeriod(updatastreamId, begin, end);
            //文本预警消息型:Alert
            case 4:
                return alertService.findListByUdsIdWithPeriod(updatastreamId, begin, end);
            default:
                throw new IllegalArgumentException("数据类型码不正确");
        }
    }
}
