package ru.kamotive.kd.listeners;

import com.haulmont.cuba.core.TransactionalDataManager;
import com.haulmont.cuba.core.app.events.AttributeChanges;
import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import com.haulmont.cuba.core.global.View;
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

    @Inject
    private TransactionalDataManager txDm;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void beforeCommit(EntityChangedEvent<Task, UUID> event) {
        if (event.getType().equals(EntityChangedEvent.Type.UPDATED)) {
            AttributeChanges changes = event.getChanges();
            if (changes.isChanged("state")){
                Task task = txDm.load(event.getEntityId()).view("task-view").one();
                if (task.getState().equals(TaskStatusEnum.DONE))
                    taskService.proceedCloseTask(task);
            }
        }
    }
}