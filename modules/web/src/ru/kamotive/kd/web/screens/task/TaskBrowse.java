package ru.kamotive.kd.web.screens.task;

import com.haulmont.cuba.gui.screen.*;
import ru.kamotive.kd.entity.Task;

@UiController("kd_Task.browse")
@UiDescriptor("task-browse.xml")
@LookupComponent("table")
@LoadDataBeforeShow
public class TaskBrowse extends MasterDetailScreen<Task> {

}