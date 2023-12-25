package com.iotplatform.backend.dao;

import com.iotplatform.backend.pojo.Downdatastream;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DowndatastreamDao {
    int save(Downdatastream downdatastream); //增加
    int delete(String id);//删除

    //多对一查询e
    List<Downdatastream> findByDeviceId(String deviceId); //根据设备id查

    //询向下通道(带单位)
    //根据通道类型查询模板上下通道
    List<Downdatastream> findByType(@Param("devId") String devId, @Param("data_type") Integer data_type);

    Downdatastream findById(String id);//根据id查询
}
