package com.example.demo.service;

import com.example.demo.config.db.DataSourceNames;
import com.example.demo.config.db.TargetDataSource;
import com.example.demo.dao.UserMapper;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
	private UserMapper userMapper;

    public int insertUser(User record){
        return userMapper.insertUser(record);
    }

    @TargetDataSource(DataSourceNames.DB_TEST_READ)
    public User findUser(User record){
        return userMapper.findUser(record);
    }

    public int updateUser(User record){
        return userMapper.updateUser(record);
    }

    public int countUserByName(String name){
        return userMapper.countUserByName(name);
    }

    public List<User> findUserList(User record){
        return userMapper.findUserList(record);
    }


}