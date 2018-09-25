package com.example.demo.service;

import com.example.demo.BaseTest;
import com.example.demo.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by liulanhua on 2018/9/21.
 */
public class UserServiceTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void insertUser() throws Exception {
    }

    @Test
    public void findUser() throws Exception {
        User record = new User();
        record.setId("1");
        record = userService.findUser(record);
        System.out.println("=============================================================");
        System.out.println(record);
    }

    @Test
    public void updateUser() throws Exception {
    }

    @Test
    public void countUserByName() throws Exception {
    }

    @Test
    public void findUserList() throws Exception {
    }

}