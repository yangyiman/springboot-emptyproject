package com.yym.springboot.validated.service.impl;

import com.yym.springboot.validated.entity.User;
import com.yym.springboot.validated.service.ValidatedService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
public class ValidatedServiceImpl implements ValidatedService {
    @Override
    public boolean add3(@Validated User user) {
        return false;
    }
}
