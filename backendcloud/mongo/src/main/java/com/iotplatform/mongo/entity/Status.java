package com.huatec.hiot_cloud.mongodb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huatec.hiot_cloud.mongodb.entity.base.UpdatastreamData;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @ Created by liwenqiang  on 2017/5/19 0019 at 上午 8:48  for hiot
 * @ Description: 开关型数据点
 */
@Document(collection = "status") //@Document - 把一个java类声明为mongodb的文档，可以通过collection参数指定这个类对应的文档。
//@CompoundIndex - 复合索引的声明，建复合索引可以有效地提高多字段的查询效率。
@CompoundIndexes({
        @CompoundIndex(name = "timing_idx", def = "{'upDataStreamId': 1, 'timing': -1}")
})
public class Status extends UpdatastreamData {
    //向上数据通道
    @Indexed//(unique = true)//@Indexed - 声明该字段需要索引，建索引可以大大的提高查询效率。
    private String updatastreamId;
    //时间戳
    @Indexed
    @JsonFormat(pattern= "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date timing;
    //状态（0：关，1：开）
    private Integer status;

    public String getUpdatastreamId() {
        return updatastreamId;
    }

    public void setUpdatastreamId(String updatastreamId) {
        this.updatastreamId = updatastreamId;
    }

    public Date getTiming() {
        return timing;
    }

    public void setTiming(Date timing) {
        this.timing = timing;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Status{" +
                "updatastreamId='" + updatastreamId + '\'' +
                ", timing=" + timing +
                ", status=" + status +
                '}';
    }
}
