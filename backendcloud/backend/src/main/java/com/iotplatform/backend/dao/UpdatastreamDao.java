package com.iotplatform.backend.dao;

import com.iotplatform.backend.pojo.Updatastream;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UpdatastreamDao {
    int save(Updatastream updatastream);//增加向上通道
    List<Updatastream> findByDevId(String id);//根据设备id查询所有通道
    int deleteById(String Id);//删除
    //根据通道类型查询设备上下通道
    List<Updatastream> findByType(@Param("devId") String devId,
                                         @Param("data_type") Integer data_type);
    //根据ID查询通道数据类型
    Integer findDataTypeById(String id);
}