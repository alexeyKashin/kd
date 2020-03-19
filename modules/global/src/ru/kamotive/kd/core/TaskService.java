package ru.kamotive.kd.core;

import ru.kamotive.kd.entity.Task;

public interface TaskService {
    String NAME = "kd_TaskService";

    void proceedCloseTask(Task task);
}