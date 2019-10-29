package com.wy.hodgepodge.common.dynamic.impl;


import com.wy.hodgepodge.common.dynamic.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public void add() {
        System.out.println("--------------------add---------------");

    }
}
