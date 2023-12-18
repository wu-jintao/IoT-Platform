package com.huatec.hiot_cloud.mongodb.dao.impl;

import com.huatec.hiot_cloud.mongodb.dao.IBaseDao;
import com.huatec.hiot_cloud.mongodb.entity.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @ Created by liwenqiang  on 2017/12/20 0020 at 下午 2:49  for mongo
 * @ Description:
 */
@Repository
public class AlertDao implements IBaseDao<Alert>{
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void createCollection() {
        if (!mongoTemplate.collectionExists(Alert.class)) {
            mongoTemplate.createCollection(Alert.class);
        }
    }

    @Override
    public void insert(Alert entity) {
        mongoTemplate.insert(entity);
    }

    @Override
    public Alert findLatestOneByUdsId(String updatastreamId) {
        Query query = query(where(udsId).is(updatastreamId))
                .with(new Sort(Sort.Direction.DESC,timing));
        return mongoTemplate.findOne(query,Alert.class);
    }

    @Override
    public List<Alert> findListByUdsId(String updatastreamId, int skip, int limit) {
        Query query = query(where(udsId).is(updatastreamId))
                .with(new Sort(Sort.Direction.DESC,timing))
                .skip(skip).limit(limit);
        return mongoTemplate.find(query,Alert.class);
    }

    @Override
    public List<Alert> findListByUdsIdWithPeriod(String updatastreamId, Date begin, Date end) {
        Query query = query(where(udsId).is(updatastreamId).and(timing).gte(begin).lte(end))
                .with(new Sort(Sort.Direction.DESC,timing));
        return mongoTemplate.find(query,Alert.class);
    }
}
