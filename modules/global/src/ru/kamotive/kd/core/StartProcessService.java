package ru.kamotive.kd.core;

import com.haulmont.cuba.security.entity.User;
import ru.kamotive.kd.dto.CamundaProcessResult;

public interface StartProcessService {
    String NAME = "kd_StartProcessService";

    CamundaProcessResult startProcess(User author, User receiver, String inn);
}