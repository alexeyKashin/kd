package ru.kamotive.kd.web.screens.camundainstance;

import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kamotive.kd.core.GetActiveInstancesService;
import ru.kamotive.kd.entity.CamundaInstance;

import javax.inject.Inject;
import java.util.List;

@UiController("kd_CamundaInstance.browse")
@UiDescriptor("camunda-instance-browse.xml")
@LookupComponent("camundaInstancesTable")
public class CamundaInstanceBrowse extends StandardLookup<CamundaInstance> {

    @Inject
    private CollectionContainer<CamundaInstance> camundaInstancesDc;
    @Inject
    private GetActiveInstancesService service;
    private final Logger log = LoggerFactory.getLogger(CamundaInstanceBrowse.class);

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        List<CamundaInstance> activeInstances = service.getActiveInstances();
        activeInstances.forEach(camundaInstance -> camundaInstancesDc.getMutableItems().add(camundaInstance));
    }
}