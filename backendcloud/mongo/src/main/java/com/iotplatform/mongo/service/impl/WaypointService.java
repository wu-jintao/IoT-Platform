package com.iotplatform.mongo.service.impl;

import com.iotplatform.mongo.dao.impl.WaypointDao;
import com.iotplatform.mongo.entity.Waypoint;
import com.iotplatform.mongo.service.IWaypointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ Created by liwenqiang  on 2017/12/23 0023 at 下午 3:16  for mongo_integration
 * @ Description:
 */
@Service
public class WaypointService implements IWaypointService{
    @Autowired
    private WaypointDao waypointDao;

    @Override
    public void insert(Waypoint waypoint) {
        waypointDao.insert(waypoint);
    }

    @Override
    public Waypoint findLatestOneByUdsId(String updatastreamId) {
        return waypointDao.findLatestOneByUdsId(updatastreamId);
    }

    @Override
    public List<Waypoint> findListByUdsId(String updatastreamId, int skip, int limit) {
        return waypointDao.findListByUdsId(updatastreamId, skip, limit);
    }

    @Override
    public List<Waypoint> findListByUdsIdWithPeriod(String updatastreamId, Date begin, Date end) {
        return waypointDao.findListByUdsIdWithPeriod(updatastreamId, begin, end);
    }
}
