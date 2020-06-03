package com.yym.springboot.soap.service;

import com.yym.springboot.soap.entity.Employee;
import com.yym.springboot.soap.entity.EmployeeDetailRequest;
import com.yym.springboot.soap.entity.EmployeeDetailResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class SoapService {
    public static final String ns = "http://www.yym.com/employee";

    @PayloadRoot(namespace = ns,localPart = "EmployeeDetailRequest")
    @ResponsePayload
    public EmployeeDetailResponse getByName(@RequestPayload EmployeeDetailRequest request){
        EmployeeDetailResponse response = new EmployeeDetailResponse();
        Employee emp = new Employee();
        emp.setName(request.getName());
        emp.setEmail(request.getName()+"@com");
        emp.setCode("200");
        response.setEmployee(emp);
        return response;
    }
}
