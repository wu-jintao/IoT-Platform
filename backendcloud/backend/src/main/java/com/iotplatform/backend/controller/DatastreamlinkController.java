package com.iotplatform.backend.controller;

import com.iotplatform.backend.pojo.Datastreamlink;
import com.iotplatform.backend.pojo.Result;
import com.iotplatform.backend.pojo.Updatastream;
import com.iotplatform.backend.service.IDatastreamlinkService;
import com.iotplatform.backend.service.IDowndatastreamService;
import com.iotplatform.backend.service.IUpdatastreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "datastream")
public class DatastreamlinkController {
    @Autowired
    private IDatastreamlinkService datastreamlinkService;
    @Autowired
    private IUpdatastreamService updatastreamService;
    @Autowired
    private IDowndatastreamService downdatastreamService;
    /*value = "创建设备通道(并关联上下通道)",notes = "创建设备双向通道（上下通道及关
联）或者向上通道"
    * deviceId:设备ID
    * title:通道名称
    * data_type:通道数据类型，data_type：<0：默认全部 1：数值2：开关 3 GPS 4：文本>
    * direct:通道方向,//0-双向通道；1-向上通道；2-向下通道
    * */
    @PostMapping(value = "{deviceId}/{title}/{data_type}/{direct}")
    public Result add(@PathVariable String deviceId, @PathVariable String
            title, @PathVariable Integer data_type, @PathVariable Integer direct){
        Datastreamlink datastreamlink = new Datastreamlink();
        datastreamlink.setTitle(title);
        try {
            //双向通道
            if(direct == 0){
                return datastreamlinkService.save(deviceId,title,data_type);
            }
            //向上通道
            if(direct == 1){
                Updatastream updatastream = new Updatastream();
                updatastream.setTitle(title);
                updatastream.setDataType(data_type);
                return updatastreamService.save(deviceId,updatastream);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("SAVE_ERROR");
        }
        return Result.error("INPUT_PARAM_ERROR");
    }
    /*(value = "查询设备向上通道对应的向下通道",notes = "根据设备向上通道查找对应向下通
道")*/
    @GetMapping(value = "{updatastreamId}/downdatastream")
    public Result findDownByUp(@PathVariable String updatastreamId){
        try{
            return datastreamlinkService.findDownByUp(updatastreamId);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("SELECT_ERROR");
        }
    }
}
