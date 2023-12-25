package com.iotplatform.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Datastreamlink implements Serializable {
    /**
     * @ Created by liwenqiang  on 2017/6/19 0019 at 下午 1:43  for hiot
     * @ Description: 设备上下通道关联（双向）表
     */
    private String id;
    private String title;
    private Updatastream updatastream;
    private Downdatastream downdatastream;
}
