package com.huatec.hiot_cloud.mongodb.service.impl;

import com.huatec.hiot_cloud.mongodb.dao.impl.MeasurementDao;
import com.huatec.hiot_cloud.mongodb.entity.Measurement;
import com.huatec.hiot_cloud.mongodb.service.IMeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ Created by liwenqiang  on 2017/12/23 0023 at 下午 3:16  for mongo_integration
 * @ Description:
 */
@Service
public class MeasurementService implements IMeasurementService{
    @Autowired
    private MeasurementDao measurementDao;

    @Override
    public void insert(Measurement measurement) {
        measurementDao.insert(measurement);
    }

    @Override
    public Measurement findLatestOneByUdsId(String updatastreamId) {
        return measurementDao.findLatestOneByUdsId(updatastreamId);
    }

    @Override
    public List<Measurement> findListByUdsId(String updatastreamId, int skip, int limit) {
        return measurementDao.findListByUdsId(updatastreamId, skip, limit);
    }

    @Override
    public List<Measurement> findListByUdsIdWithPeriod(String updatastreamId, Date begin, Date end) {
        return measurementDao.findListByUdsIdWithPeriod(updatastreamId, begin, end);
    }
}
