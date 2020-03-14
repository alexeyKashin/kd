package ru.kamotive.kd.core;

import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kamotive.kd.dto.CamundaExchange;
import ru.kamotive.kd.entity.KdExchange;

import java.time.LocalDateTime;

@Component
public class KdExchangeFactory {

    private final Metadata metadata;
    private final Persistence persistence;
    @Autowired
    public KdExchangeFactory(Metadata metadata, Persistence persistence) {
        this.metadata = metadata;
        this.persistence = persistence;
    }


    public void createExchange(User author, User receiver, CamundaExchange camundaExchange, String externalId) {
        KdExchange kdExchange = metadata.create(KdExchange.class);
        kdExchange.setAuthor(author);
        kdExchange.setReceiver(receiver);
        kdExchange.setStartDate(LocalDateTime.now());
        kdExchange.setProcessId(camundaExchange.getInstanceId());
        kdExchange.setTaskId(camundaExchange.getTaskId());
        kdExchange.setExternalId(externalId);
        persistence.runInTransaction(em -> em.persist(kdExchange));
    }
}
