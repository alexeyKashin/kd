package ru.kamotive.kd.core;

import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.TypedQuery;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.security.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import ru.kamotive.kd.KdTestContainer;
import ru.kamotive.kd.entity.KdExchange;

import java.util.List;

class MailSenderTest {

    @RegisterExtension
    public static KdTestContainer cont = KdTestContainer.Common.INSTANCE;

    private MailSender mailSender = AppBeans.get(MailSender.class);

    @Test
    void sendMailForSuccessKdDelivery() {
        Persistence persistence = cont.persistence();
        User user = persistence.callInTransaction(em -> {
            TypedQuery<User> query = em.createQuery(
                    "select u from sec$User u where u.login = :userLogin", User.class);
            query.setParameter("userLogin", "admin");
            List<User> users = query.getResultList();
            return users.get(0);
        });

        Metadata metadata = cont.metadata();
        KdExchange kdExchange = metadata.create(KdExchange.class);
        kdExchange.setReceiver(user);
        kdExchange.setDocumentLinks("12345");
        mailSender.sendMailForSuccessKdDelivery(kdExchange);
    }
}