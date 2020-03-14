package ru.kamotive.kd.core;

import ru.kamotive.kd.entity.KdExchange;

public interface ExecuteTaskService {
    String NAME = "kd_ExecuteTaskService";

    /**
     *
     * @param exchange
     */
    void executeTask(KdExchange exchange);
}