package com.iotplatform.mongo.service.impl;

import com.iotplatform.mongo.dao.impl.StatusDao;
import com.iotplatform.mongo.entity.Status;
import com.iotplatform.mongo.service.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ Created by liwenqiang  on 2017/12/23 0023 at 下午 3:16  for mongo_integration
 * @ Description:
 */
@Service
public class StatusService implements IStatusService{
    @Autowired
    private StatusDao statusDao;

    @Override
    public void insert(Status status) {
        statusDao.insert(status);
    }

    @Override
    public Status findLatestOneByUdsId(String updatastreamId) {
        return statusDao.findLatestOneByUdsId(updatastreamId);
    }

    @Override
    public List<Status> findListByUdsId(String updatastreamId, int skip, int limit) {
        return statusDao.findListByUdsId(updatastreamId, skip, limit);
    }

    @Override
    public List<Status> findListByUdsIdWithPeriod(String updatastreamId, Date begin, Date end) {
        return statusDao.findListByUdsIdWithPeriod(updatastreamId, begin, end);
    }
}
