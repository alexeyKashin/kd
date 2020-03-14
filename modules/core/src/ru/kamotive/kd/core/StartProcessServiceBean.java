package ru.kamotive.kd.core;

import com.haulmont.cuba.security.entity.User;
import org.springframework.stereotype.Service;
import ru.kamotive.kd.dto.CamundaProcessResult;
import ru.kamotive.kd.dto.ProcessResult;

@Service(StartProcessService.NAME)
public class StartProcessServiceBean implements StartProcessService {

    @Override
    public CamundaProcessResult startProcess(User author, User receiver, String inn) {
        CamundaProcessResult camundaProcessResult = new CamundaProcessResult();
        camundaProcessResult.setResult(ProcessResult.OK);
        return camundaProcessResult;
    }
}