package ru.kamotive.kd.core;

import com.haulmont.cuba.security.entity.User;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kamotive.kd.camunda.CamundaClient;
import ru.kamotive.kd.dto.CamundaExchange;
import ru.kamotive.kd.dto.CamundaProcessResult;
import ru.kamotive.kd.dto.ProcessResult;
import ru.kamotive.kd.external.ExternalClient;

@Service(StartProcessService.NAME)
public class StartProcessServiceBean implements StartProcessService {

    private final Logger log = LoggerFactory.getLogger(StartProcessServiceBean.class);
    private final CamundaClient camundaClient;
    private final KdExchangeFactory kdExchangeFactory;
    private final ExternalClient externalClient;
    @Autowired
    public StartProcessServiceBean(CamundaClient camundaClient,
                                   KdExchangeFactory kdExchangeFactory,
                                   ExternalClient externalClient) {
        this.camundaClient = camundaClient;
        this.kdExchangeFactory = kdExchangeFactory;
        this.externalClient = externalClient;
    }

    @Override
    public CamundaProcessResult startProcess(User author, User receiver, String inn) {
        try {
            log.info("Старт процесса в камунде, автор {} получатель {}", author.getName(), receiver.getName());
            CamundaExchange camundaExchange = camundaClient.executeStartProcessAndGetFirstStep(author, receiver, inn);
            log.info("Старт процесса во внешней системе");
            String externalId = externalClient.executeStart();
            log.debug("Создание записи обмена");
            kdExchangeFactory.createExchange(author, receiver, camundaExchange, externalId);
            CamundaProcessResult camundaProcessResult = new CamundaProcessResult();
            camundaProcessResult.setResult(ProcessResult.OK);
            return camundaProcessResult;
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error(ExceptionUtils.getStackTrace(e));
            CamundaProcessResult camundaProcessResult = new CamundaProcessResult();
            camundaProcessResult.setResult(ProcessResult.Error);
            camundaProcessResult.setMessage(e.getMessage());
            return camundaProcessResult;
        }
    }
}