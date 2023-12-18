package com.huatec.hiot_cloud.mongodb.dao.impl;

import com.huatec.hiot_cloud.mongodb.dao.IBaseDao;
import com.huatec.hiot_cloud.mongodb.entity.Measurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * @ Created by liwenqiang  on 2017/12/20 0020 at 下午 2:49  for mongo
 * @ Description:
 */
@Repository
public class MeasurementDao implements IBaseDao<Measurement>{
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void createCollection() {
        if (!mongoTemplate.collectionExists(Measurement.class)) {
            mongoTemplate.createCollection(Measurement.class);
        }
    }

    @Override
    public void insert(Measurement entity) {
        mongoTemplate.insert(entity);
    }

    @Override
    public Measurement findLatestOneByUdsId(String updatastreamId) {
        Query query = query(where(udsId).is(updatastreamId))
                .with(new Sort(Sort.Direction.DESC,timing));
        return mongoTemplate.findOne(query,Measurement.class);
    }

    @Override
    public List<Measurement> findListByUdsId(String updatastreamId, int skip, int limit) {
        Query query = query(where(udsId).is(updatastreamId))
                .with(new Sort(Sort.Direction.DESC,timing))
                .skip(skip).limit(limit);
        return mongoTemplate.find(query,Measurement.class);
    }

    @Override
    public List<Measurement> findListByUdsIdWithPeriod(String updatastreamId, Date begin, Date end) {
        Query query = query(where(udsId).is(updatastreamId).and(timing).gte(begin).lte(end))
                .with(new Sort(Sort.Direction.DESC,timing));
        return mongoTemplate.find(query,Measurement.class);
    }
}
