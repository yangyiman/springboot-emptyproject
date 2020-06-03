package com.yym.springboot.base.java;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yym.springboot.base.java.entity.Student;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试Fastjson
 */
public class FastJsonTest {

    @Test
    public void test1(){
        Student s1 = new Student().setId(1).setName("张一");
        Student s2 = new Student().setId(2).setName("张二");
        Student s3 = new Student().setId(3).setName("张三");

        /**
         *  javaBean ======> jsonStr
         *  String JSON.toJSONString(obj);
         *
         *  map与json字符串的区别在于:
         * 1. map的kv用=连接,而json用:连接
         * 2. map的key没有双引号,而json有
         */
        String s1JsonStr = JSON.toJSONString(s1);
        // s1JsonStr = {"id":1,"name":"张一"}
        System.out.println("s1JsonStr = " + s1JsonStr);

        Map<String,Object> map = new HashMap<>();
        map.put("id",1);
        map.put("name","李四");
        // map = {name=李四, id=1}
        System.out.println("map = " + map);

        List<Student> list = new ArrayList<>();
        list.add(s1);
        list.add(s2);
        list.add(s3);
        System.out.println("list = " + list);
        String listJsonStr = JSON.toJSONString(list);
        System.out.println("listJsonStr = " + listJsonStr);

        /**
         * jsonStr =======> javaBean
         * Object JSON.parseObject(str,clazz);
         */
        Student student = JSON.parseObject(s1JsonStr, Student.class);
        System.out.println("student = " + student);

        /**
         * javaBean ---> jsonObj
         * JSONObject JSON.toJSON(obj);
         */
        JSONObject jsonObject= (JSONObject)JSON.toJSON(s1);
        System.out.println("jsonObject = " + jsonObject);
        System.out.println("name = " + jsonObject.get("name"));

        /**
         * jsonObj =====> javaBean
         * Object JSON.toJavaObject(JsonOBj,clazz);
         */
        Student student1 = JSON.toJavaObject(jsonObject, Student.class);
        System.out.println("student1 = " + student1);

        /**
         * javaList ====> JsonArray
         */
        JSONArray array = (JSONArray) JSON.toJSON(list);
        System.out.println("array = " + array);

        /**
         * jsonArray ===> javaList
         */
        List list1 = JSON.toJavaObject(array, List.class);
        System.out.println("list1 = " + list1);
    }

    @Test
    public void test2(){
        JSONObject jsonObject = JSON.parseObject(null);
        System.out.println("jsonObject = " + jsonObject);
    }
}
