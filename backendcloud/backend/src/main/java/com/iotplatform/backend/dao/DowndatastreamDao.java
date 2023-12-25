package com.iotplatform.backend.dao;

import com.iotplatform.backend.pojo.Downdatastream;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DowndatastreamDao {
    public int save(Downdatastream downdatastream);//增加
    public int delete(String id);//删除
    //多对一查询e
    public List<Downdatastream> findByDeviceId(String deviceId);//根据设备id查
    //询向下通道(带单位)
    //根据通道类型查询模板上下通道
    public List<Downdatastream> findByType(@Param("devId") String devId,
                                           @Param("data_type") Integer data_type);
    public Downdatastream findById(String id);//根据id查询
}
