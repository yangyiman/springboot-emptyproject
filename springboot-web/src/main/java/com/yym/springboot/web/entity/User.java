package com.yym.springboot.web.entity;

import com.yym.springboot.web.annotation.FieldList;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import java.beans.Transient;
import java.io.Serializable;
import java.util.List;

// 测试以逗号分隔可以转换为数组么
// 测试以逗号分隔可以转换为集合么
@Data
public class User implements Serializable {
    //private List<Integer> array;
    @FieldList("listInteger")
    @JsonIgnore
    private String listInteger;
    private String desc;
}
