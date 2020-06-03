package com.yym.springboot.soap.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class SoapConfig {

    /**
     * MessageDispatcherServlet用于处理soap请求,并且将context传入其中,便于获取其他bean
     *
     * @param applicationContext
     * @return
     */
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    /**
     *使用XsdSchema暴露了一个标准的的WSDL 1.1，bean的名字emp 将会作为wsdl 暴露出去的名称，我们可以通过http://localhost/ws/emp.wsdl路径访问
     *
     * @param schema
     * @return
     */
    @Bean(name = "emp")
    public Wsdl11Definition defaultWsdl11Definition(XsdSchema schema) {
        DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
        // 类似于端口
        wsdl.setPortTypeName("EmpPort");
        // 置顶访问接口的全路径,需要以ws为前缀
        wsdl.setLocationUri("/ws/getEmp");
        //wsdl.setTargetNamespace("http://www.yym.com/employee1");
        wsdl.setSchema(schema);
        return wsdl;
    }

    @Bean
    public XsdSchema employeeSchema() {
        return new SimpleXsdSchema(new ClassPathResource("employee.xsd"));
    }
}
