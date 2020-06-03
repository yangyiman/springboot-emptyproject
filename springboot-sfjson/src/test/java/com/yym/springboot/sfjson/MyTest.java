package com.yym.springboot.sfjson;

import com.yym.springboot.sfjson.entity.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class MyTest {
    // 创建JSONObject
    @Test
    public void test1(){
        JSONObject json = new JSONObject();
        json.put("name","jack");
        json.put("age",24);
        System.out.println("json = " + json);

        // 增减属性
        json.put("email","123!@COM");
        System.out.println("json = " + json);

        // 获取属性
        Integer  age = (Integer) json.get("age");
        System.out.println("age = " + age);

        // 是否为空,是否为数组
        boolean array = json.isArray();
        boolean empty = json.isEmpty();
        boolean nullObject = json.isNullObject();

        List<String> list = new ArrayList<>();
        list.add("football");
        list.add("movie");
        json.put("hobby",list);
        System.out.println("json = " + json);
    }

    // JsonArray
    @Test
    public void test2(){
        JSONArray array = new JSONArray();
        array.add(1);
        array.add(2);
        array.add(4);
        System.out.println("array = " + array);
        // 指定位置添加元素,从0开始
        array.add(2,"heh");
        System.out.println("array = " + array);
    }

    //bean和json之间的转化
    @Test
    public void test3(){
        User user =new User().setName("hello").setAge(20);
        System.out.println("user = " + user);
        // bean转json
        JSONObject json = new JSONObject();
        JSONObject jsonObject = JSONObject.fromObject(user);
        System.out.println("jsonObject = " + jsonObject);

        // json转bean
        String str = "{\"age\":20,\"name\":\"hello\"}";
        JSONObject jsonObject1 = JSONObject.fromObject(str);
        User user1 = (User) JSONObject.toBean(jsonObject1, User.class);
        System.out.println("user1 = " + user1);
        User uesr2 = (User) JSONObject.toBean(jsonObject);
        System.out.println("uesr2 = " + uesr2);
    }


    @Test
    public void test4(){
        final String channelStr = "http://172.25.4.13/201808/api/redirect.jsp?redirectUrl=%40channel%40013&returnUrl=http%3A%2F%2F172.25.4.13%2F201808%2Fpages%2Fhome%2F%3Ffocus%3DGuide-0%26active-Guide%3DGuide-0%26returnUrl%3D";
        String strd = "http://172.25.4.13/201808/api/redirect.jsp?redirectUrl=@channel@013&returnUrl=http://172.25.4.13/201808/pages/home/?focus=Guide-0&active-Guide=Guide-0&returnUrl=";
        String vodStr = "http://122.191.50.141:33200/EPG/jsp/defaultnew1/en/201808/pages/vod-single-detail/index.html?typeId=&vodId=628272&returnUrl=http%3A%2F%2F172.25.4.13%2F201808%2Fpages%2Fhome%2F%3Ffocus%3DGuide-1%26active-Guide%3DGuide-1%26returnUrl%3D";
        String vodStrd = "http://122.191.50.141:33200/EPG/jsp/defaultnew1/en/201808/pages/vod-single-detail/index.html?typeId=&vodId=628272&returnUrl=http://172.25.4.13/201808/pages/home/?focus=Guide-1&active-Guide=Guide-1&returnUrl=";
        String a = "http://172.25.4.13/201808/api/redirect.jsp?redirectUrl=%40detail%4099180001000000010000000073777555&returnUrl=http%3A%2F%2F172.25.4.13%2F201808%2Fpages%2Fhome%2F%3Ffocus%3DRecommendFirst-0%26active-Guide%3DGuide-4%26returnUrl%3D";
        String specialUrl = "http://172.25.4.13/201808/api/redirect.jsp?redirectUrl=%40special%40special-mod%2FmodHD25.jsp%3FtypeID%3D10000100000000090000000000010692%26columnCategoryCode%3D10000100000000090000000000045734&returnUrl=http%3A%2F%2F172.25.4.13%2F201808%2Fpages%2Fhome%2F%3Ffocus%3DMoreScreenList1-1%26active-Guide%3DGuide-1%26returnUrl%3Dhttp://172.25.4.13/201808/api/redirect.jsp?redirectUrl=%40special%40special-mod%2FmodHD25.jsp%3FtypeID%3D10000100000000090000000000010692%26columnCategoryCode%3D10000100000000090000000000045734&returnUrl=http%3A%2F%2F172.25.4.13%2F201808%2Fpages%2Fhome%2F%3Ffocus%3DMoreScreenList1-1%26active-Guide%3DGuide-1%26returnUrl%3D";
        String decode = URLDecoder.decode(specialUrl);
        System.out.println("decode = " + decode);
    }

}
