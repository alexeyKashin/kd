package ru.kamotive.kd.core;

import com.haulmont.cuba.core.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kamotive.kd.camunda.CamundaClient;
import ru.kamotive.kd.entity.ExchangeStatus;
import ru.kamotive.kd.entity.KdExchange;
import ru.kamotive.kd.external.ExternalClient;

import java.time.LocalDateTime;
import java.util.List;

@Service(ExternalSystemRequestSchedulerService.NAME)
public class ExternalSystemRequestSchedulerServiceBean implements ExternalSystemRequestSchedulerService {
    private final Logger log = LoggerFactory.getLogger(ExternalSystemRequestSchedulerServiceBean.class);
    private final Persistence persistence;
    private final ExternalClient externalClient;
    private final CamundaClient camundaClient;
    private final MailSender mailSender;
    @Autowired
    public ExternalSystemRequestSchedulerServiceBean(Persistence persistence,
                                                     ExternalClient externalClient,
                                                     CamundaClient camundaClient,
                                                     MailSender mailSender) {
        this.persistence = persistence;
        this.externalClient = externalClient;
        this.camundaClient = camundaClient;
        this.mailSender = mailSender;
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
        String links = externalClient.getLinks();
        if (links == null) {
            log.debug("{} документы не сформированы", exchange.getId());
            return;
        }

        log.debug("{} документы сформированы", exchange.getId());
        persistence.runInTransaction(em -> {
            exchange.setDocumentLinks(links);
            exchange.setState(ExchangeStatus.DELIVERED);
            exchange.setReceiveDate(LocalDateTime.now());
            em.merge(exchange);
        });
        log.debug("{} завршение шага в камунде", exchange.getId());
        camundaClient.completeRequestTask(exchange);
        log.debug("{} отправка уведомления пользователю", exchange.getId());
        mailSender.sendMailForSuccessKdDelivery(exchange);
    }

    private List<KdExchange> findExchanges() {
        return persistence.callInTransaction(em -> em
                .createQuery("select e from kd_KdExchange e where e.state = :state", KdExchange.class)
                .setParameter("state", ExchangeStatus.IN_PROCESS)
                .getResultList());
    }
}