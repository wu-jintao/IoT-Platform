package com.iotplatform.backend.dao;

import com.iotplatform.backend.pojo.Datastreamlink;
import com.iotplatform.backend.pojo.Downdatastream;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DatastreamlinkDao {
    //关联设备上下通道
    int save(Datastreamlink datastreamlink);
    //根据向上通道ID查找对应向下通道ID
    String findDownByUp(String updatastream_id);
    //根据向上通道ID删除双向通道
    int deleteByUpdatastreamId(String id);
    //根据向下通道ID删除双向通道
    int deleteByDowndatastreamId(String id);
}
