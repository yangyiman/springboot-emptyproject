package com.springboot.html2image.controller;

import gui.ava.html.image.generator.HtmlImageGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.UUID;

@Controller
public class mainController {

    // 行分隔符
    final static public String LS = System.getProperty("line.separator", "/n");
    // 文件分割符
    final static public String FS = System.getProperty("file.separator", "//");
    // 当网页超出目标大小时 截取
    final static public int maxWidth = 2000;
    final static public int maxHeight = 2000;

    @GetMapping("/gen")
    @ResponseBody
    public void gen(@RequestParam String fileName, HttpServletResponse response) throws IOException {
        HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
        //imageGenerator.loadHtml(htmlString);
//        imageGenerator.loadUrl("http://localhost:8080/hello?s=" + URLEncoder.encode(fileName, "utf-8"));
        imageGenerator.loadUrl("http://baidu.com");
        imageGenerator.saveAsImage("d:/hello-world-" + UUID.randomUUID() + ".png");

        // 将生成的图片展示到客户端
        BufferedImage bufferedImage = imageGenerator.getBufferedImage();

        //Dimension size = imageGenerator.getSize();

        //size.setSize(4000, 4000);
        //imageGenerator.setSize(size);
        //System.out.println("size = " + size);

        ServletOutputStream out = response.getOutputStream();
        // 输出流转换 = 将BufferedImage转换为InputStream
        //ByteArrayOutputStream os = new ByteArrayOutputStream();
//        ImageIO.write(bufferedImage, "png", os);
        ImageIO.write(bufferedImage, "png", out);
        // 使用out写到浏览器
       /* ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = is.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }*/
    }

    @GetMapping("/hello")
    public String getHello(@RequestParam String s) {
        System.out.println("s = " + s);
        return "hello";
    }

    // 以javascript脚本获得网页全屏后大小
    public static String getScreenWidthHeight() {

        StringBuffer jsDimension = new StringBuffer();
        jsDimension.append("var width = 0;").append(LS);
        jsDimension.append("var height = 0;").append(LS);
        jsDimension.append("if(document.documentElement) {").append(LS);
        jsDimension
                .append("  width = Math.max(width, document.documentElement.scrollWidth);")
                .append(LS);
        jsDimension
                .append("  height = Math.max(height, document.documentElement.scrollHeight);")
                .append(LS);
        jsDimension.append("}").append(LS);
        jsDimension.append("if(self.innerWidth) {").append(LS);
        jsDimension.append("  width = Math.max(width, self.innerWidth);")
                .append(LS);
        jsDimension.append("  height = Math.max(height, self.innerHeight);")
                .append(LS);
        jsDimension.append("}").append(LS);
        jsDimension.append("if(document.body.scrollWidth) {").append(LS);
        jsDimension.append(
                "  width = Math.max(width, document.body.scrollWidth);")
                .append(LS);
        jsDimension.append(
                "  height = Math.max(height, document.body.scrollHeight);")
                .append(LS);
        jsDimension.append("}").append(LS);
        jsDimension.append("return width + ':' + height;");

        return jsDimension.toString();
    }

    public void switchToPic(InputStream inputStream, OutputStream out, String fileName) throws IOException {
        /*StringBuffer out1 = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n; (n = inputStream.read(b)) != -1; ) {
            out1.append(new String(b, 0, n));
        }*/
        //String html = out1.toString();
        String html = h;
        HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
        imageGenerator.getBufferedImage();
        imageGenerator.loadHtml(html);
        //这里如果指定了盘符，可以直接存在本地，自己本地写demo的话可以用
        String imageName = "D:\\jpg\\" + UUID.randomUUID().toString() + ".png";
        //imageGenerator.saveAsImage(fileName);
        imageGenerator.saveAsImage(imageName);
        //输出流转换 = 将BufferedImage转换为InputStream桥接
        BufferedImage buffimg = imageGenerator.getBufferedImage();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(buffimg, "png", os);
        //输出流将生成的png文件输出到浏览器中
        InputStream input = new ByteArrayInputStream(os.toByteArray());
        byte[] buffer = new byte[512];
        int bytesToRead = -1;
        while ((bytesToRead = input.read(buffer)) != -1) {
            out.write(buffer, 0, bytesToRead);
        }
    }

    public String htmlString = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "  <head>\n" +
            "    <title>\n" +
            "      抗击疫情,成长有我\n" +
            "    </title>\n" +
            "    <meta name=\"viewport\" content=\"initial-scale=1.0, user-scalable=no\" />\n" +
            "    <meta charset=\"UTF-8\" />\n" +
            "\n" +
            "    <!--引入外网jquery-->\n" +
            "    <script src=\"https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js\"></script>\n" +
            "    <script type=\"text/javascript\">\n" +
            "      function GetRequest() {\n" +
            "        var url = location.search; //获取url中\"?\"符后的字串\n" +
            "        var theRequest = new Object();\n" +
            "        if (url.indexOf(\"?\") != -1) {\n" +
            "          var str = url.substr(1);\n" +
            "          strs = str.split(\"&\");\n" +
            "          for(var i = 0; i < strs.length; i ++) {\n" +
            "            theRequest[strs[i].split(\"=\")[0]]=decodeURI(strs[i].split(\"=\")[1]);\n" +
            "          }\n" +
            "        }\n" +
            "        return theRequest;\n" +
            "      }\n" +
            "      $(function() {\n" +
            "        var Request = new Object();\n" +
            "        Request = GetRequest();\n" +
            "        var phoneNum = Request[\"phoneNum\"];\n" +
            "        var childrenName = Request[\"childrenName\"];\n" +
            "        // http://127.0.0.1:8677\n" +
            "        var url = \"/api/v1/yfb-certification/list\";//?phoneNum=\"+phoneNum+\"&childrenName=\"+childrenName;\n" +
            "\n" +
            "        // var phoneNum =  \"${phoneNum }\";\n" +
            "        // var childrenName =  \"${childrenName }\";\n" +
            "        // window.location.href = url;\n" +
            "        $.ajax({\n" +
            "          type: \"GET\",\n" +
            "          url: url,\n" +
            "          data: {\"phoneNum\":phoneNum,\"childrenName\":childrenName},\n" +
            "          dataType:'text',\n" +
            "          async:false,\n" +
            "          success: function(data){\n" +
            "            data = JSON.parse(data);\n" +
            "            // alert(data.data.childrenName);\n" +
            "            // alert(data.data.phoneNum);\n" +
            "            // alert(data.data.prizeName);\n" +
            "            var childName = data.data.childrenName;\n" +
            "            var works = data.data.works;\n" +
            "            $('#childrenName').empty();   //清空childrenName里面的所有内容\n" +
            "            $('#works').empty();   //清空prizeName里面的所有内容\n" +
            "            $('#childrenName').html(childName);\n" +
            "            $('#works').html(works);\n" +
            "\n" +
            "            //控制姓名字体大小\n" +
            "            if (childName.length>=10) {\n" +
            "              $('#childrenName').addClass('sm');\n" +
            "            }else if (childName.length>=5) {\n" +
            "              $('#childrenName').addClass('md');\n" +
            "            }else {\n" +
            "              $('#childrenName').addClass('lg');\n" +
            "            }\n" +
            "\n" +
            "            //控制作品字体大小\n" +
            "            if (works.length>15) {\n" +
            "              $('#works').addClass('sm');\n" +
            "            }else if (works.length>10) {\n" +
            "              $('#works').addClass('md');\n" +
            "            }else if(works.length>5){\n" +
            "              $('#works').addClass('lg');\n" +
            "            }else {\n" +
            "              $('#works').addClass('mi');\n" +
            "            }\n" +
            "          }\n" +
            "        });\n" +
            "\n" +
            "      });\n" +
            "    </script>\n" +
            "    <style type=\"text/css\">\n" +
            "      body {\n" +
            "        padding: 0;\n" +
            "        margin: 0;\n" +
            "        overflow-x: hidden;\n" +
            "        overflow-y: hidden;\n" +
            "      }\n" +
            "      div span {\n" +
            "        box-sizing: border-box;\n" +
            "      }\n" +
            "      .full-screen {\n" +
            "        /*height: 675px;*/\n" +
            "        /*width: 375px;*/\n" +
            "        height: 1230px;\n" +
            "        width: 660px;\n" +
            "        background-image: url('https://static.hbtv.com.cn/certi-search/fine.jpg');\n" +
            "        background-repeat: no-repeat;\n" +
            "        background-size: 100% 100%;\n" +
            "      }\n" +
            "\n" +
            "      .userName {\n" +
            "        position: absolute;\n" +
            "        top: 565px;\n" +
            "        /*font-size: 27px;*/\n" +
            "        left: 50px;\n" +
            "        font-weight: 700;\n" +
            "        color: #7c5b30;\n" +
            "      }\n" +
            "      .friend {\n" +
            "        font-size: 30px;\n" +
            "        font-weight: 800;\n" +
            "        margin-left: 8px;\n" +
            "        position: relative;\n" +
            "        top: 4px;\n" +
            "      }\n" +
            "      .user-name-blank {\n" +
            "        color: #333;\n" +
            "        padding: 0 2px 6px;\n" +
            "        border-bottom: 2px solid #d0af82;\n" +
            "        font-size: 28px;\n" +
            "      }\n" +
            "      .user-name-blank.lg {\n" +
            "        font-size: 28px;\n" +
            "      }\n" +
            "      .user-name-blank.md {\n" +
            "        font-size: 24px;\n" +
            "      }\n" +
            "      .user-name-blank.sm {\n" +
            "        font-size: 20px;\n" +
            "      }\n" +
            "\n" +
            "      .reportName {\n" +
            "        font-size: 27px;\n" +
            "        position: absolute;\n" +
            "        top: 655px;\n" +
            "        left: 249px;\n" +
            "        font-weight: 700;\n" +
            "      }\n" +
            "      .reportName.mi {\n" +
            "        font-size: 28px;\n" +
            "        left: 350px;\n" +
            "      }\n" +
            "      .reportName.lg {\n" +
            "        font-size: 26px;\n" +
            "        left: 320px;\n" +
            "      }\n" +
            "      .reportName.md {\n" +
            "        font-size: 22px;\n" +
            "        left: 260px;\n" +
            "      }\n" +
            "      .reportName.sm {\n" +
            "        font-size: 18px;\n" +
            "      }\n" +
            "\n" +
            "    </style>\n" +
            "  </head>\n" +
            "\n" +
            "  <body>\n" +
            "    <div class=\"full-screen\">\n" +
            "      <div class=\"userName\">\n" +
            "        <span class=\"user-name-blank\" id=\"childrenName\"></span\n" +
            "        ><span class=\"friend\" id=\"spanFriend\">小朋友</span>\n" +
            "      </div>\n" +
            "      <div class=\"reportName\" id=\"works\">\n" +
            "\n" +
            "      </div>\n" +
            "    </div>\n" +
            "  </body>\n" +
            "</html>\n" +
            "\n" +
            "<!-- userName -->\n" +
            "<!-- lt 6 lg -->\n" +
            "<!-- 6-10 md -->\n" +
            "<!-- gte 10 sm -->\n" +
            "\n" +
            "<!-- reportName -->\n" +
            "<!-- lt 11 lg -->\n" +
            "<!-- 11-14 md-->\n" +
            "<!-- gte 14 sm-->\n";

    String h = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>hello</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "    你好,再见,我要将html生成图片\n" +
            "</body>\n" +
            "</html>";
}
