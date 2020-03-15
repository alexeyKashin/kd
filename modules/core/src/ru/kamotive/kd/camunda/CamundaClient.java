package ru.kamotive.kd.camunda;

import com.haulmont.cuba.security.entity.User;
import ru.kamotive.kd.dto.CamundaExchange;
import ru.kamotive.kd.entity.CamundaInstance;
import ru.kamotive.kd.entity.KdExchange;

import java.util.List;

public interface CamundaClient {

    /**
     * Старт процесса и переход на первый шаг
     * @param author
     * @param receiver
     * @param inn
     * @return ид процесса
     */
    CamundaExchange executeStartProcessAndGetFirstStep(User author, User receiver, String inn);

    /**
     * Выполнение таска с запросом
     * @return
     */
    CamundaExchange completeRequestTask(KdExchange exchange);

    /**
     * Выплнение таска ознакомления
     * @param exchange
     * @return
     */
    CamundaExchange completeDeliveryTask(KdExchange exchange);

    /**
     * Получить спиок активных процессов
     * @return
     */
    List<CamundaInstance> getActiveInstances();
}
