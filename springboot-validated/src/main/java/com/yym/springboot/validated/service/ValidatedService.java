package com.yym.springboot.validated.service;

import com.yym.springboot.validated.entity.User;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

public interface ValidatedService {
    boolean add3(@Valid User user);
}
