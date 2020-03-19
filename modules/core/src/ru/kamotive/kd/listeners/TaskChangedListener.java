package ru.kamotive.kd.listeners;

import com.haulmont.cuba.core.TransactionalDataManager;
import com.haulmont.cuba.core.app.events.AttributeChanges;
import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import ru.kamotive.kd.core.TaskService;
import ru.kamotive.kd.entity.Task;
import ru.kamotive.kd.entity.TaskStatusEnum;

import javax.inject.Inject;
import java.util.UUID;

@Component("kd_TaskChangedListener")
public class TaskChangedListener {
    @Inject
    private TaskService taskService;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void beforeCommit(EntityChangedEvent<Task, UUID> event) {
        Task task = (Task) event.getSource();
        if (event.getType().equals(EntityChangedEvent.Type.UPDATED)) {
            AttributeChanges changes = event.getChanges();
            if (changes.isChanged("state") && task.getState().equals(TaskStatusEnum.DONE)){
                taskService.proceedCloseTask(task);
            }
        }
    }
}