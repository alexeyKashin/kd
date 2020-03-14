package ru.kamotive.kd.core;

import com.haulmont.cuba.core.app.EmailerAPI;
import com.haulmont.cuba.core.global.EmailInfo;
import com.haulmont.cuba.core.global.EmailInfoBuilder;
import com.haulmont.cuba.security.entity.User;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kamotive.kd.entity.KdExchange;

@Component
public class MailSender {

    private final Logger log = LoggerFactory.getLogger(MailSender.class);
    private final EmailerAPI emailerAPI;
    private final EmailInfoBuilder emailInfoBuilder;
    @Autowired
    public MailSender(EmailerAPI emailerAPI, EmailInfoBuilder emailInfoBuilder) {
        this.emailerAPI = emailerAPI;
        this.emailInfoBuilder = emailInfoBuilder;
    }

    public void sendMailForSuccessKdDelivery(KdExchange exchange) {
        log.debug("{} Попытка отправки email по доставке КД", exchange.getId());
        User receiver = exchange.getReceiver();
        if (receiver.getEmail() == null) {
            log.debug("Не заполнено поле email у пользователя {}", receiver.getName());
            return;
        }

        try {
            EmailInfo emailInfo = emailInfoBuilder
                    .setAddresses(receiver.getEmail())
                    .setCaption("Доставка КД")
                    .setBody("Доставка КД\n" + exchange.getDocumentLinks())
                    .build();
            emailerAPI.sendEmail(emailInfo);
        } catch (Exception e) {
            log.error("Ошибка при отправке email " + e.getMessage());
            log.error(ExceptionUtils.getStackTrace(e));
        }
    }
}
