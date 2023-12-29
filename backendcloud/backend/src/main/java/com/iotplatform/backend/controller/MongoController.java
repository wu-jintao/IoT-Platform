package com.iotplatform.backend.controller;

import com.iotplatform.backend.pojo.Result;
import com.iotplatform.backend.service.ext.IMongoService;
import com.iotplatform.mongo.entity.base.UpdatastreamData;
import com.iotplatform.mongo.manager.IMongoApiManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("mongo")
public class MongoController {
    @Autowired
    private IMongoApiManager mongoApiManager;
    @Autowired
    private IMongoService mongoService;

    /*按向上通道ID保存设备数据,说明如下：" +
            "1、向上通道ID(updatastreamId)必填" +
            "2、deviceData：数据，" +
            "如果数据类型为3（地理位置型），则按\"longitude,latitude,elevation\"字符串形式，其中\",\"是英文逗号*/
    @PostMapping("/upload/{updatastreamId}/{deviceData}")
    public Result upload(@PathVariable String updatastreamId, @PathVariable String deviceData){
        Integer dataType = mongoService.findDataTypeByUdsId(updatastreamId);
        if(dataType == null){
            return Result.error("DATASTREAM_NOT_FOUND");
        }
        UpdatastreamData updatastreamData = mongoService.getUdsData(updatastreamId,dataType,deviceData);
        mongoApiManager.upload(dataType,updatastreamData);
        return Result.success(updatastreamData);
    }

    /*查询某一设备某一通道最近一条数据*/
    @GetMapping(value="/download/{updatastreamId}/one")
    public Result downloadLastedOneByUdsId(@PathVariable String updatastreamId){
        Integer dataType = mongoService.findDataTypeByUdsId(updatastreamId);
        UpdatastreamData updatastreamData = mongoApiManager.downloadLastedOneByUdsId(dataType,updatastreamId);
        return Result.success(updatastreamData);
    }

    /*查询某一设备某一通道指定位置指定数量数据
     * updatastreamId:向上通道ID
     * skip:起始条数
     * limit:总条数
     * */
    @GetMapping(value="/download/{updatastreamId}/{skip}/{limit}/List")
    public Result downloadListByUdsId(@PathVariable String updatastreamId,@PathVariable Integer skip,@PathVariable Integer limit){
        Integer dataType = mongoService.findDataTypeByUdsId(updatastreamId);
        List<? extends UpdatastreamData> updatastreamDataList = mongoApiManager.downloadListByUdsId(dataType,updatastreamId,skip,limit);
        return Result.success(updatastreamDataList);
    }

    /*查询某一设备某一通道某一时间段的数据
     * updatastreamId:向上通道ID
     * begin:起始时间,时间格式：yyyy-MM-dd HH:mm
     * end:结束时间,时间格式：yyyy-MM-dd HH:mm
     * */
    @GetMapping(value="/download/{updatastreamId}/period/List")
    public Result downloadListByUdsIdWithPeriod(@PathVariable String updatastreamId,
                                                @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date begin,
                                                @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date end){
        Integer dataType = mongoService.findDataTypeByUdsId(updatastreamId);
        List<? extends UpdatastreamData> updatastreamDataList = mongoApiManager.downloadListByUdsIdWithPeriod(dataType,updatastreamId,begin,end);
        return Result.success(updatastreamDataList);
    }
}