package com.iotplatform.backend.dao;

import com.iotplatform.backend.pojo.Device;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface DeviceMapper {
    @Select("select * from devices where user_id=#{userId} ORDER BY ifnull(updated, created) desc,created desc,dev_type,title")
    public List<Device> findDevicesByUserId(String userId);

    /* 新建设备。*/
    /* 新建设备。*/
    @Insert("insert into devices(id, title, dev_type, user_id, created, updated, deviceimg, dev_status, description, mac)" +
            " values(#{id},#{title},#{devType},#{userId},#{created},#{updated},#{deviceImg},#{devStatus},#{description},#{mac})")
    public int add(Device device);

    /*更新设备*/
    public int update(Device device);
}