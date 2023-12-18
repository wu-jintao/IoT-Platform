package com.huatec.hiot_cloud.mongodb.dao.impl;

import com.huatec.hiot_cloud.mongodb.dao.IBaseDao;
import com.huatec.hiot_cloud.mongodb.entity.Waypoint;
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
public class WaypointDao implements IBaseDao<Waypoint>{
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void createCollection() {
        if (!mongoTemplate.collectionExists(Waypoint.class)) {
            mongoTemplate.createCollection(Waypoint.class);
        }
    }

    @Override
    public void insert(Waypoint entity) {
        mongoTemplate.insert(entity);
    }

    @Override
    public Waypoint findLatestOneByUdsId(String updatastreamId) {
        Query query = query(where(udsId).is(updatastreamId))
                .with(new Sort(Sort.Direction.DESC,timing));
        return mongoTemplate.findOne(query,Waypoint.class);
    }

    @Override
    public List<Waypoint> findListByUdsId(String updatastreamId, int skip, int limit) {
        Query query = query(where(udsId).is(updatastreamId))
                .with(new Sort(Sort.Direction.DESC,timing))
                .skip(skip).limit(limit);
        return mongoTemplate.find(query,Waypoint.class);
    }

    @Override
    public List<Waypoint> findListByUdsIdWithPeriod(String updatastreamId, Date begin, Date end) {
        Query query = query(where(udsId).is(updatastreamId).and(timing).gte(begin).lte(end))
                .with(new Sort(Sort.Direction.DESC,timing));
        return mongoTemplate.find(query,Waypoint.class);
    }
}
