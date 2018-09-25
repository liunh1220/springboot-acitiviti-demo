package com.example.demo.controller;

import com.example.demo.manager.ActivitiManager;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by liulanhua on 2018/9/25.
 */
@RestController
public class TestController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @GetMapping(name="user/{id}")
    public String findUser(String id) throws Exception {
        User record = new User();
        record.setId(id);
        record = userService.findUser(record);
        System.out.println("=============================================================");
        System.out.println(record);
        return record.toString();
    }



    @RequestMapping(value="processes/findTask/{assignee}",method = RequestMethod.GET)
    public String findTask(@PathVariable("assignee") String assignee) {
        List<Task> list = ActivitiManager.findPersonalTask(assignee);
        return ActivitiManager.activitiTaskWriteOut(list);
    }


}
