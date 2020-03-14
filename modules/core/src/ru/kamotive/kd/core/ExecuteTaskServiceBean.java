package ru.kamotive.kd.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kamotive.kd.camunda.CamundaClient;
import ru.kamotive.kd.entity.KdExchange;

@Service(ExecuteTaskService.NAME)
public class ExecuteTaskServiceBean implements ExecuteTaskService {

    private final Logger log = LoggerFactory.getLogger(ExecuteTaskServiceBean.class);
    private final CamundaClient camundaClient;
    @Autowired
    public ExecuteTaskServiceBean(CamundaClient camundaClient) {
        this.camundaClient = camundaClient;
    }

    /**
     * @param exchange
     */
    @Override
    public void executeTask(KdExchange exchange) {
        camundaClient.completeDeliveryTask(exchange);
    }
}