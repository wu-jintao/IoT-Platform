package com.iotplatform.mongo.dao;

import java.util.Date;
import java.util.List;

/**
 * @ Created by liwenqiang  on 2017/12/20 0020 at 下午 2:16  for mongo
 * @ Description:
 */
public interface IBaseDao<T> {
    /**文档字段名：updatastreamId*/
    public static final String udsId = "updatastreamId";
    /**文档字段名：timing*/
    public static final String timing = "timing";

    /**创建对象文档*/
    public void createCollection();
    /**对象文档中插入数据*/
    public void insert(T entity);
    /**根据向上通道ID查询最新一条数据*/
    public T findLatestOneByUdsId(String updatastreamId);
    /**根据向上通道ID查询指定位置指定条数的数据*/
    public List<T> findListByUdsId(String updatastreamId,int skip, int limit);
    /**根据向上通道ID查询指定起讫时间点的数据*/
    public List<T> findListByUdsIdWithPeriod(String updatastreamId,Date begin,Date end);
}
