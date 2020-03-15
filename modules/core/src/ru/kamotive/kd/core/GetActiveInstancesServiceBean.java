package ru.kamotive.kd.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kamotive.kd.camunda.CamundaClient;
import ru.kamotive.kd.entity.CamundaInstance;

import java.util.List;

@Service(GetActiveInstancesService.NAME)
public class GetActiveInstancesServiceBean implements GetActiveInstancesService {

    private final CamundaClient camundaClient;
    @Autowired
    public GetActiveInstancesServiceBean(CamundaClient camundaClient) {
        this.camundaClient = camundaClient;
    }


    @Override
    public List<CamundaInstance> getActiveInstances() {
        return null;
    }
}