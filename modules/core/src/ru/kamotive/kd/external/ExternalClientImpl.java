package ru.kamotive.kd.external;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.kamotive.kd.config.KdConfig;
import ru.kamotive.kd.dto.ExternalExchange;
import ru.kamotive.kd.entity.KdExchange;

@Component
public class ExternalClientImpl implements ExternalClient {
    private static final String FINISH_STATE = "finish";
    private final Logger log = LoggerFactory.getLogger(ExternalClientImpl.class);
    private final RestTemplate restTemplate;
    private final KdConfig kdConfig;
    private final Gson gson = new Gson();
    @Autowired
    public ExternalClientImpl(KdConfig kdConfig) {
        this.kdConfig = kdConfig;

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectionRequestTimeout(2000);
        factory.setConnectTimeout(2000);
        factory.setReadTimeout(2000);

        this.restTemplate = new RestTemplate(factory);
    }


    @Override
    public String executeStart() {
        log.info("Запрос на старт процесса формирования документов");
        String startProcessUrl = kdConfig.getStartProcessUrlTemplate();
        log.debug("Запрос по url {}", startProcessUrl);
        ResponseEntity<String> response = restTemplate.exchange(startProcessUrl, HttpMethod.GET,
                new HttpEntity<>("", getHeaders()), String.class);

        ExternalExchange externalExchange = gson.fromJson(response.getBody(), ExternalExchange.class);
        log.debug("Получен идентификатор {}", externalExchange.getId());
        return externalExchange.getId();
    }

    @Override
    public String getLinks(KdExchange exchange) {
        log.debug("{} проверка сформированных документов", exchange.getId());
        String checkStateUrlTemplate = kdConfig.getCheckStateUrlTemplate();
        String checkStateUrl = String.format(checkStateUrlTemplate, exchange.getExternalId());
        log.debug("Запрос по url {}", checkStateUrl);
        ResponseEntity<String> checkStateResponse = restTemplate.exchange(checkStateUrl, HttpMethod.GET,
                new HttpEntity<>("", getHeaders()), String.class);

        ExternalExchange externalExchange = gson.fromJson(checkStateResponse.getBody(), ExternalExchange.class);
        if (!StringUtils.equals(FINISH_STATE, externalExchange.getState())) {
            log.debug("{} получен статус {}", exchange.getId(), externalExchange.getState());
            return null;
        }

        String linksUrlTemplate = kdConfig.getLinksUrlTemplate();
        String linksUrl = String.format(linksUrlTemplate, exchange.getExternalId());
        log.debug("{} получение документов", exchange.getId());
        log.debug("Запрос по url {}", linksUrl);
        ResponseEntity<String> linksResponse = restTemplate.exchange(linksUrl, HttpMethod.GET,
                new HttpEntity<>("", getHeaders()), String.class);
        ExternalExchange linksExchange = gson.fromJson(linksResponse.getBody(), ExternalExchange.class);
        return linksExchange.getLinks();
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
