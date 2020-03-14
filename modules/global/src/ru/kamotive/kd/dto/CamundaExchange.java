package ru.kamotive.kd.dto;

import java.io.Serializable;
import java.util.Map;

public class CamundaExchange implements Serializable {
    private String instanceId;
    private String taskId;
    private Map<String, String> taskVariables;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Map<String, String> getTaskVariables() {
        return taskVariables;
    }

    public void setTaskVariables(Map<String, String> taskVariables) {
        this.taskVariables = taskVariables;
    }
}
