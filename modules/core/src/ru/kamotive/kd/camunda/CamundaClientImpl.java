package ru.kamotive.kd.camunda;

import com.google.gson.Gson;
import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.security.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.kamotive.kd.config.KdConfig;
import ru.kamotive.kd.dto.CamundaExchange;
import ru.kamotive.kd.dto.InstanceExchange;
import ru.kamotive.kd.dto.TaskExchange;
import ru.kamotive.kd.entity.KdExchange;

import java.util.Map;

@Component
public class CamundaClientImpl implements CamundaClient {
    private final Logger log = LoggerFactory.getLogger(CamundaClientImpl.class);
    private final RestTemplate restTemplate;
    private final KdConfig kdConfig;
    private Gson gson = new Gson();

    @Autowired
    public CamundaClientImpl(KdConfig kdConfig) {
        this.kdConfig = kdConfig;

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectionRequestTimeout(2000);
        factory.setConnectTimeout(2000);
        factory.setReadTimeout(2000);

        this.restTemplate = new RestTemplate(factory);
    }

    /**
     * Старт процесса и переход на первый шаг
     *
     * @param author
     * @param receiver
     * @param inn
     * @return ид процесса
     */
    @Override
    public CamundaExchange executeStartProcessAndGetFirstStep(User author, User receiver, String inn) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Старт процесса
        Map<String, Object> bodyVariables = ParamsMap.of("author", author.getId(),
                "receiver", receiver.getId(), "inn", inn);
        Map<String, Object> bodyMap = ParamsMap.of("variables", bodyVariables);

        String startUrl = kdConfig.getCamundaRestUrl() + "process-definition/key/" + kdConfig.getCamundaProcessCode() + "/start";
        ResponseEntity<String> startExchange = restTemplate.exchange(startUrl, HttpMethod.POST,
                new HttpEntity<>(gson.toJson(bodyMap), headers), String.class);

        InstanceExchange instanceExchange = gson.fromJson(startExchange.getBody(), InstanceExchange.class);
        String definitionId = instanceExchange.getDefinitionId();

        // Запрос текущего шага
        String taskUrl = kdConfig.getCamundaRestUrl() + "task?processInstanceId=" + definitionId;
        ResponseEntity<String> taskExchange = restTemplate.exchange(taskUrl, HttpMethod.GET,
                new HttpEntity<>("", headers), String.class);
        TaskExchange[] taskExchanges = gson.fromJson(taskExchange.getBody(), TaskExchange[].class);
        TaskExchange taskInfo = taskExchanges[0];

        CamundaExchange camundaExchange = new CamundaExchange();
        camundaExchange.setInstanceId(definitionId);
        camundaExchange.setTaskId(taskInfo.getId());

        return camundaExchange;
    }

    /**
     * Выполнение таска с запросом
     *
     * @param exchange
     * @return
     */
    @Override
    public CamundaExchange completeRequestTask(KdExchange exchange) {
        return null;
    }

    /**
     * Выплнение таска ознакомления
     *
     * @param exchange
     * @return
     */
    @Override
    public CamundaExchange completeDeliveryTask(KdExchange exchange) {
        return null;
    }
}