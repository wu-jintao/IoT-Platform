package com.huatec.hiot_cloud.mongodb.test;

import com.huatec.hiot_cloud.mongodb.dao.impl.MeasurementDao;
import com.huatec.hiot_cloud.mongodb.entity.Measurement;
import com.huatec.hiot_cloud.mongodb.entity.base.UpdatastreamData;
import com.huatec.hiot_cloud.mongodb.manager.IMongoApiManager;
import com.huatec.hiot_cloud.mongodb.service.impl.MeasurementService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @ Created by liwenqiang  on 2017/12/19 0019 at 下午 3:37  for mongo
 * @ Description:
 */
//Spring + Junit4集成测试
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-mongo.xml")
public class MongoTest {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private MeasurementDao measurementDao;
    @Autowired
    private MeasurementService measurementService;
    @Autowired
    private IMongoApiManager mongoApiManager;

    @Test
    public void testMongo(){
        String dbName = mongoTemplate.getDb().getName();
        System.out.println(dbName);
    }
    @Test
    public void testMeasurementDao(){
        //创建文档
        measurementDao.createCollection();
        //插入文档
        Measurement measurement = new Measurement();
        measurement.setUpdatastreamId("aaa");
        measurement.setValue("25");
        measurement.setTiming(new Date());
        measurementDao.insert(measurement);

        //根据udsId查询一条数据
        Measurement m1 = measurementDao.findLatestOneByUdsId("aaa");
        if(m1!=null){
            System.out.println("插入成功");
            System.out.println("----------------------------------------");
            System.out.println("根据updatastreamId<aaa>查询成功");
            System.out.println(m1);
            System.out.println("----------------------------------------");
        }

        //再插入一条文档
        Measurement measurement1 = new Measurement();
        measurement1.setUpdatastreamId("aaa");
        measurement1.setValue("30");
        measurement1.setTiming(new Date());
        measurementDao.insert(measurement1);

        //根据udsId查询指定位置指定条数的数据
        List<Measurement> measurementList = measurementDao.findListByUdsId("aaa",0,10);
        System.out.println("根据updatastreamId<aaa>查询指定位置<0>指定条数<10>的数据成功");
        for(Measurement m:measurementList){
            System.out.println(m);
        }
        System.out.println("----------------------------------------");

        //根据udsId查询起讫时间点的数据
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE,-1);
        List<Measurement> measurementList1 = measurementDao.findListByUdsIdWithPeriod("aaa", calendar.getTime(),new Date());
        System.out.println("根据updatastreamId<aaa>查询昨天到今天这段时间的数据");
        for(Measurement m:measurementList1){
            System.out.println(m);
        }
        System.out.println("----------------------------------------");
    }
    @Test
    public void testMeasurementService(){
        //插入文档
        Measurement measurement = new Measurement();
        measurement.setUpdatastreamId("bbb");
        measurement.setValue("26");
        measurement.setTiming(new Date());
        measurementService.insert(measurement);

        //根据udsId查询一条数据
        Measurement m1 = measurementService.findLatestOneByUdsId("bbb");
        if(m1!=null){
            System.out.println("插入成功");
            System.out.println("----------------------------------------");
            System.out.println("根据updatastreamId<bbb>查询成功");
            System.out.println(m1);
            System.out.println("----------------------------------------");
        }

        //再插入一条文档
        Measurement measurement1 = new Measurement();
        measurement1.setUpdatastreamId("bbb");
        measurement1.setValue("27");
        measurement1.setTiming(new Date());
        measurementService.insert(measurement1);

        //根据udsId查询指定位置指定条数的数据
        List<Measurement> measurementList = measurementService.findListByUdsId("bbb",0,10);
        System.out.println("根据updatastreamId<bbb>查询指定位置<0>指定条数<10>的数据成功");
        for(Measurement m:measurementList){
            System.out.println(m);
        }
        System.out.println("----------------------------------------");

        //根据udsId查询起讫时间点的数据
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE,-1);
        List<Measurement> measurementList1 = measurementService.findListByUdsIdWithPeriod("bbb", calendar.getTime(),new Date());
        System.out.println("根据updatastreamId<bbb>查询昨天到今天这段时间的数据");
        for(Measurement m:measurementList1){
            System.out.println(m);
        }
        System.out.println("----------------------------------------");
    }
    @Test
    public void testMongoApiManager(){
        //插入文档
        Measurement measurement = new Measurement();
        measurement.setUpdatastreamId("ccc");
        measurement.setValue("25");
        measurement.setTiming(new Date());
        mongoApiManager.upload(1,measurement);

        //根据udsId查询一条数据
        Measurement m1 = (Measurement) mongoApiManager.downloadLastedOneByUdsId(1,"ccc");
        if(m1!=null){
            System.out.println("插入成功");
            System.out.println("----------------------------------------");
            System.out.println("根据updatastreamId<aaa>查询成功");
            System.out.println(m1);
            System.out.println("----------------------------------------");
        }

        //再插入一条文档
        Measurement measurement1 = new Measurement();
        measurement1.setUpdatastreamId("ccc");
        measurement1.setValue("30");
        measurement1.setTiming(new Date());
        mongoApiManager.upload(1,measurement1);

        //根据udsId查询指定位置指定条数的数据
        List<Measurement> measurementList = ( List<Measurement>) mongoApiManager.downloadListByUdsId(1,"ccc",0,10);
        System.out.println("根据updatastreamId<aaa>查询指定位置<0>指定条数<10>的数据成功");
        for(Measurement m:measurementList){
            System.out.println(m);
        }
        System.out.println("----------------------------------------");

        //根据udsId查询起讫时间点的数据
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE,-1);
        List<Measurement> measurementList1 = ( List<Measurement>) mongoApiManager.downloadListByUdsIdWithPeriod(1,"ccc", calendar.getTime(),new Date());
        System.out.println("根据updatastreamId<aaa>查询昨天到今天这段时间的数据");
        for(Measurement m:measurementList1){
            System.out.println(m);
        }
        System.out.println("----------------------------------------");

       /* List<Measurement> measurementList = ( List<Measurement>) mongoApiManager.downloadListByUdsId(5,"ccc",0,10);
        System.out.println("根据updatastreamId<aaa>查询指定位置<0>指定条数<10>的数据成功");
        for(Measurement m:measurementList){
            System.out.println(m);
        }
        System.out.println("----------------------------------------");*/
    }
}
