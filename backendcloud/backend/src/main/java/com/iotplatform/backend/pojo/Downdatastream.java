package com.iotplatform.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Downdatastream implements Serializable {
    private String id;
    private String title;
    private Integer dataType;
    //多对一
    private Device device;
    private String unit;
    private String mark;
    /**方向标识，向上为1，向下为2，非数据库字段*/
    private Integer direction;
}