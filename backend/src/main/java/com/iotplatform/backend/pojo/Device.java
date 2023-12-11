package com.iotplatform.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Device implements Serializable {
    private String id;
    private String title;
    private String devType;
    private String mac;
    /**四种状态：
     *0.未激活-未联网的新设备
     *1.激活-新设备初次需要使用需要先激活
     ******以下两种暂时不用
     *******3.联网-设备使用中处于联网状态
     *******4.离网-设备使用中处于离网状态
     */
    private Integer devStatus;
    private LocalDateTime created;
    private LocalDateTime updated;
    private String deviceImg;
    private String description;
    private String userId;
}