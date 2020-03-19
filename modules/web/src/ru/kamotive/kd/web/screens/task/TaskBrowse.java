package ru.kamotive.kd.web.screens.task;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.global.UserSession;
import ru.kamotive.kd.core.TaskService;
import ru.kamotive.kd.entity.Task;
import ru.kamotive.kd.entity.TaskStatusEnum;

import javax.inject.Inject;

@UiController("kd_Task.browse")
@UiDescriptor("task-browse.xml")
@LookupComponent("table")
@LoadDataBeforeShow
public class TaskBrowse extends MasterDetailScreen<Task> {
    @Inject
    private CollectionLoader<Task> tasksDl;
    @Inject
    private GroupTable<Task> table;

    @Inject
    private UserSession userSession;
    @Inject
    private Notifications notifications;
    @Inject
    private DataManager dataManager;
    @Inject
    private TaskService taskService;

    @Subscribe
    public void onInit(InitEvent event) {
        tasksDl.setQuery("select e from kd_Task e where e.user.id = '" + userSession.getCurrentOrSubstitutedUser().getId() + "'");
    }

    @Subscribe("endTask")
    public void onEndTaskClick(Button.ClickEvent event) {
        Task task = table.getSingleSelected();

        if (task.getState().equals(TaskStatusEnum.DONE))
            notifications.create().withCaption("Задача уже завершена!").show();
        else {
            task.setState(TaskStatusEnum.DONE);
            dataManager.commit(task);
        }
    }
    
    
}