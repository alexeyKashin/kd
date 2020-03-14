package ru.kamotive.kd.dto;

import java.io.Serializable;

public class CamundaProcessResult implements Serializable {
    private ProcessResult result;
    private String message;

    public ProcessResult getResult() {
        return result;
    }

    public void setResult(ProcessResult result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
