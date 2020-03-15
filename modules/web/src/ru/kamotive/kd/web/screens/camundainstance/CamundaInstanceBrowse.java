package ru.kamotive.kd.web.screens.camundainstance;

import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.*;
import ru.kamotive.kd.core.GetActiveInstancesService;
import ru.kamotive.kd.entity.CamundaInstance;

import javax.inject.Inject;

@UiController("kd_CamundaInstance.browse")
@UiDescriptor("camunda-instance-browse.xml")
@LookupComponent("camundaInstancesTable")
@LoadDataBeforeShow
public class CamundaInstanceBrowse extends StandardLookup<CamundaInstance> {

    @Inject
    private CollectionContainer<CamundaInstance> camundaInstancesDc;
    @Inject
    private GetActiveInstancesService service;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        camundaInstancesDc.setItems(service.getActiveInstances());
    }
}