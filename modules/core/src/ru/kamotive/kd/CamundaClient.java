package ru.kamotive.kd;

import com.haulmont.cuba.security.entity.User;
import ru.kamotive.kd.dto.CamundaExchange;
import ru.kamotive.kd.entity.KdExchange;

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
}
