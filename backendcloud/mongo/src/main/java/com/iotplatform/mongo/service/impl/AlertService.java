package com.iotplatform.mongo.service.impl;

import com.iotplatform.mongo.dao.impl.AlertDao;
import com.iotplatform.mongo.entity.Alert;
import com.iotplatform.mongo.service.IAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ Created by liwenqiang  on 2017/12/23 0023 at 下午 3:16  for mongo_integration
 * @ Description:
 */
@Service
public class AlertService implements IAlertService {
    @Autowired
    private AlertDao alertDao;

    @Override
    public void insert(Alert alert) {
        alertDao.insert(alert);
    }

    @Override
    public Alert findLatestOneByUdsId(String updatastreamId) {
        return alertDao.findLatestOneByUdsId(updatastreamId);
    }

    @Override
    public List<Alert> findListByUdsId(String updatastreamId, int skip, int limit) {
        return alertDao.findListByUdsId(updatastreamId, skip, limit);
    }

    @Override
    public List<Alert> findListByUdsIdWithPeriod(String updatastreamId, Date begin, Date end) {
        return alertDao.findListByUdsIdWithPeriod(updatastreamId, begin, end);
    }
}
