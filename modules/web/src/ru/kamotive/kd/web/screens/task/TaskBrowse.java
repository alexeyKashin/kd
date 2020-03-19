package ru.kamotive.kd.web.screens.task;

import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.global.UserSession;
import ru.kamotive.kd.entity.Task;

import javax.inject.Inject;

@UiController("kd_Task.browse")
@UiDescriptor("task-browse.xml")
@LookupComponent("table")
@LoadDataBeforeShow
public class TaskBrowse extends MasterDetailScreen<Task> {
    @Inject
    private CollectionLoader<Task> tasksDl;

    @Inject
    private UserSession userSession;

    @Subscribe
    public void onInit(InitEvent event) {
        tasksDl.setQuery("select e from kd_Task e where e.user.id = '" + userSession.getCurrentOrSubstitutedUser().getId() + "'");
    }


}