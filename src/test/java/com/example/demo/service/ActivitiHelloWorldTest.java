package com.example.demo.service;

import com.example.demo.BaseTest;
import com.example.demo.manager.ActivitiManager;
import org.junit.Test;

public class ActivitiHelloWorldTest extends BaseTest {

    /**
     * 部署流程定义
     */
    @Test
    public void deploymentProcessDefinition() {
        String processesName = "helloworld入门程序";
        String processesBpmn = "diagrams/helloworld.bpmn";
        String processesPng = "diagrams/helloworld.png";
        ActivitiManager.deploymentProcessDefinition(processesName, processesBpmn, processesPng);
    }

    /**
     * 启动流程实例
     */
    @Test
    public void startProcessInstance() {
        //流程定义的key
        String processDefinitionKey = "helloworld";
        ActivitiManager.startProcessInstance(processDefinitionKey);
    }


    /**
     * 查询当前人的个人任务
     */
    @Test
    public void findMyPersonalTask() {
        String assignee = "李四";
        ActivitiManager.findPersonalTask(assignee);
    }

    /**
     * 完成我的任务
     */
    @Test
    public void completeMyPersonalTask() {
        //任务ID
        String taskId = "302";
        ActivitiManager.completePersonalTask(taskId);
    }

}
