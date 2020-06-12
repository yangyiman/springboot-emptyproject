package com.yym.springboot.common.entity;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResultModel {
    private Integer code;
    private String msg;
    private Object data;

    public ResultModel() {
    }

    public ResultModel(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultModel(Integer code, String msg, Object data) {
        this(code, msg);
        this.data = data;
    }

    public static ResultModel success200WithOperationNoData() {
        return new ResultModel(200, "操作成功");
    }

    public static ResultModel fail500WithOperation() {
        return new ResultModel(500, "操作失败");
    }

    public static ResultModel successNoData(Integer code, String msg) {
        return new ResultModel(code, msg);
    }

    public static ResultModel success(Integer code, String msg, Object data) {
        return new ResultModel(code, msg, data);
    }

    public static ResultModel success200WithOperation(Object data) {
        return new ResultModel(200, "操作成功", data);
    }

    public static ResultModel fail(Integer code, String msg) {
        return new ResultModel(code, msg);
    }

    public static String success200WithOperation2Json() {
        return JSON.toJSONString(success200WithOperationNoData());
    }

    public static String fail500WithOperation2Json() {
        return JSON.toJSONString(fail500WithOperation());
    }

    public static String successNoData2Json(Integer code, String msg) {
        return JSON.toJSONString(successNoData(code, msg));
    }


    public static String success2Json(Integer code, String msg, Object data) {
        return JSON.toJSONString(success(code, msg, data));
    }

    public static String fail2Json(Integer code, String msg) {
        return JSON.toJSONString(fail(code, msg));
    }

    public static String success200WithOperation2Json(Object data) {
        return JSON.toJSONString(success200WithOperation(data));
    }

}
