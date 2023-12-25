package com.iotplatform.backend.dto;

import com.iotplatform.backend.pojo.Device;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by lwq on 2017/6/8.
 * 用于设备上下通道数据的统一封装，并增加通道方向标识。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatastreamDto implements Serializable {
    private String id;
    private String title;
    private Integer data_type;
    //设备通道方向标识
    private Integer direction;
    //多对一
    private Device device;
    private String unit;
    private String mark;
}