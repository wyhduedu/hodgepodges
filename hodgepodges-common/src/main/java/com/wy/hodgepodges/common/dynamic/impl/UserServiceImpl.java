package com.wy.hodgepodges.common.dynamic.impl;


import com.wy.hodgepodges.common.dynamic.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public void add() {
        System.out.println("--------------------add---------------");

    }
}
