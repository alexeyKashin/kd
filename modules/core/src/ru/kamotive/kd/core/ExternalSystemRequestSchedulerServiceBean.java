package ru.kamotive.kd.core;

import com.haulmont.cuba.core.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kamotive.kd.entity.ExchangeStatus;
import ru.kamotive.kd.entity.KdExchange;
import ru.kamotive.kd.external.ExternalClient;

import java.util.List;

@Service(ExternalSystemRequestSchedulerService.NAME)
public class ExternalSystemRequestSchedulerServiceBean implements ExternalSystemRequestSchedulerService {
    private final Logger log = LoggerFactory.getLogger(ExternalSystemRequestSchedulerServiceBean.class);
    private final Persistence persistence;
    private final ExternalClient externalClient;
    @Autowired
    public ExternalSystemRequestSchedulerServiceBean(Persistence persistence,
                                                     ExternalClient externalClient) {
        this.persistence = persistence;
        this.externalClient = externalClient;
    }

    @Override
    public void checkCreatedDocuments() {
        log.debug("Старт проверки созданных документов во внешней системе");
        List<KdExchange> waitingExchanges = findExchanges();
        waitingExchanges.forEach(this::getDocumentsLink);
        log.debug("Окончание проверки созданных документов во внешней системе");
    }

    private void getDocumentsLink(KdExchange exchange) {
        log.debug("Проверка обмена {}", exchange.getId());

    }

    private List<KdExchange> findExchanges() {
        return persistence.callInTransaction(em -> em
                .createQuery("select e from kd_KdExchange e where e.state = :state", KdExchange.class)
                .setParameter("state", ExchangeStatus.IN_PROCESS)
                .getResultList());
    }
}