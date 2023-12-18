package com.huatec.hiot_cloud.mongodb.dao.impl;

import com.huatec.hiot_cloud.mongodb.dao.IBaseDao;
import com.huatec.hiot_cloud.mongodb.entity.Status;
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
public class StatusDao implements IBaseDao<Status>{
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void createCollection() {
        if (!mongoTemplate.collectionExists(Status.class)) {
            mongoTemplate.createCollection(Status.class);
        }
    }

    @Override
    public void insert(Status entity) {
        mongoTemplate.insert(entity);
    }

    @Override
    public Status findLatestOneByUdsId(String updatastreamId) {
        Query query = query(where(udsId).is(updatastreamId))
                .with(new Sort(Sort.Direction.DESC,timing));
        return mongoTemplate.findOne(query,Status.class);
    }

    @Override
    public List<Status> findListByUdsId(String updatastreamId, int skip, int limit) {
        Query query = query(where(udsId).is(updatastreamId))
                .with(new Sort(Sort.Direction.DESC,timing))
                .skip(skip).limit(limit);
        return mongoTemplate.find(query,Status.class);
    }

    @Override
    public List<Status> findListByUdsIdWithPeriod(String updatastreamId, Date begin, Date end) {
        Query query = query(where(udsId).is(updatastreamId).and(timing).gte(begin).lte(end))
                .with(new Sort(Sort.Direction.DESC,timing));
        return mongoTemplate.find(query,Status.class);
    }
}
