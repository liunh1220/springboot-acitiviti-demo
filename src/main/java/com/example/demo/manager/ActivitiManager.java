package com.example.demo.manager;

import com.example.demo.util.SpringUtils;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ActivitiManager {

    private static Logger logger = LoggerFactory.getLogger(ActivitiManager.class);

    /**
     * 部署流程定义
     *
     * @param processesName
     * @param processesBpmn
     * @param processesPng
     */
    public static Deployment deploymentProcessDefinition(String processesName, String processesBpmn, String processesPng) {
        Deployment deployment = SpringUtils.getBean("repositoryService", RepositoryService.class)//与流程定义和部署对象相关的Service
                .createDeployment()//创建一个部署对象
                .name(processesName)//添加部署的名称
                .addClasspathResource(processesBpmn)//从classpath的资源中加载，一次只能加载一个文件
                .addClasspathResource(processesPng)//从classpath的资源中加载，一次只能加载一个文件
                .deploy();//完成部署
        logger.debug("部署ID：" + deployment.getId());
        logger.debug("部署名称：" + deployment.getName());
        return deployment;
    }

    /**
     * 启动流程实例
     *
     * @param processDefinitionKey
     */
    public static ProcessInstance startProcessInstance(String processDefinitionKey) {
        //流程定义的key
        ProcessInstance pi = SpringUtils.getBean("runtimeService", RuntimeService.class)//与正在执行的流程实例和执行对象相关的Service
                .startProcessInstanceByKey(processDefinitionKey);//使用流程定义的key启动流程实例，key对应*.bpmn文件中id的属性值，使用key值启动，默认是按照最新版本的流程定义启动
        logger.debug("流程实例ID:" + pi.getId());//流程实例ID
        logger.debug("流程定义ID:" + pi.getProcessDefinitionId());//流程定义ID
        return pi;
    }


    /**
     * 查询当前人的个人任务
     *
     * @param assignee 办理人
     */
    public static List<Task> findPersonalTask(String assignee) {
        List<Task> list = SpringUtils.getBean("taskService", TaskService.class)//与正在执行的任务管理相关的Service
                .createTaskQuery()//创建任务查询对象
                .taskAssignee(assignee)//指定个人任务查询，指定办理人
                .list();
        activitiTaskWriteOut(list);
        return list;
    }

    /**
     * 输出当前人的个人任务
     * @param list
     * @return
     */
    public static String activitiTaskWriteOut(List<Task> list){
        StringBuilder stringBuilder = null;
        if (list != null && list.size() > 0) {
            stringBuilder = new StringBuilder();
            for (Task task : list) {
                stringBuilder.append("任务ID:" + task.getId());
                stringBuilder.append(", 任务名称:" + task.getName());
                stringBuilder.append(", 任务的创建时间:" + task.getCreateTime());
                stringBuilder.append(", 任务的办理人:" + task.getAssignee());
                stringBuilder.append(", 流程实例ID：" + task.getProcessInstanceId());
                stringBuilder.append(", 执行对象ID:" + task.getExecutionId());
                stringBuilder.append(", 流程定义ID:" + task.getProcessDefinitionId());
                logger.debug(stringBuilder.toString());
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 完成我的任务
     *
     * @param taskId
     */
    public static String completePersonalTask(String taskId) {
        //任务ID    与正在执行的任务管理相关的Service
        SpringUtils.getBean("taskService", TaskService.class).complete(taskId);
        logger.debug("完成任务：任务ID：" + taskId);
        return "完成任务：任务ID：" + taskId;
    }


}
