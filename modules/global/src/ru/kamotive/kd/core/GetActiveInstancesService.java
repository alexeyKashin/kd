package ru.kamotive.kd.core;

import ru.kamotive.kd.entity.CamundaInstance;

import java.util.List;

public interface GetActiveInstancesService {
    String NAME = "kd_GetActiveInstancesService";

    List<CamundaInstance> getActiveInstances();
}