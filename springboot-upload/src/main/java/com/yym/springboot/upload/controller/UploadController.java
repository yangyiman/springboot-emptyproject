package com.yym.springboot.upload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class UploadController {

    @PostMapping("/upload")
    @ResponseBody
    public String file(@RequestBody MultipartFile file) throws IOException {
        if(file.isEmpty()){
            return "请选择一个文件";
        }
        String fileName = file.getOriginalFilename();
        file.transferTo(new File("D:/Idea/IdeaProjects/springboot-empty-project/springboot-upload/src/main/static/"+fileName));
        return "上传成功";
    }

    @GetMapping("/page")
    public String page(){
        return "upload";
    }
}
