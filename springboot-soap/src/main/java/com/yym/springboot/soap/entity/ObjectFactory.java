//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2020.01.07 时间 11:24:07 AM CST 
//


package com.yym.springboot.soap.entity;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.yym.springboot.soap.entity package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.yym.springboot.soap.entity
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EmployeeDetailRequest }
     * 
     */
    public EmployeeDetailRequest createEmployeeDetailRequest() {
        return new EmployeeDetailRequest();
    }

    /**
     * Create an instance of {@link EmployeeDetailResponse }
     * 
     */
    public EmployeeDetailResponse createEmployeeDetailResponse() {
        return new EmployeeDetailResponse();
    }

    /**
     * Create an instance of {@link Employee }
     * 
     */
    public Employee createEmployee() {
        return new Employee();
    }

}
