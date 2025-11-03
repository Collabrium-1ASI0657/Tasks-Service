package nrg.inc.synhubbackend.tasks.application;

import nrg.inc.synhubbackend.tasks.interfaces.rest.resources.TaskResource;
import nrg.inc.synhubbackend.tasks.interfaces.rest.resources.UpdateTaskResource;
import nrg.inc.synhubbackend.tasks.domain.model.queries.GetAllTaskByStatusQuery;
import nrg.inc.synhubbackend.tasks.domain.model.queries.GetTaskByIdQuery;
import nrg.inc.synhubbackend.tasks.domain.model.commands.UpdateTaskCommand;
import nrg.inc.synhubbackend.tasks.domain.model.commands.UpdateTaskStatusCommand;
import nrg.inc.synhubbackend.tasks.domain.model.commands.DeleteTaskCommand;
import nrg.inc.synhubbackend.tasks.domain.services.TaskCommandService;
import nrg.inc.synhubbackend.tasks.domain.services.TaskQueryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskFacadeLocal implements TaskFacade {

    private final TaskQueryService taskQueryService;
    private final TaskCommandService taskCommandService;

    public TaskFacadeLocal(TaskQueryService taskQueryService, TaskCommandService taskCommandService) {
        this.taskQueryService = taskQueryService;
        this.taskCommandService = taskCommandService;
    }

    @Override
    public TaskResource getTaskById(Long taskId) {
        var q = new GetTaskByIdQuery(taskId);
        return this.taskQueryService.handle(q);
    }

    @Override
    public List<TaskResource> getAllTasksByStatus(String status) {
        var q = new GetAllTaskByStatusQuery(status);
        return this.taskQueryService.handle(q);
    }

    @Override
    public TaskResource updateTaskStatus(Long taskId, String status) {
        var cmd = new UpdateTaskStatusCommand(taskId, status);
        return this.taskCommandService.handle(cmd);
    }

    @Override
    public TaskResource updateTask(Long taskId, UpdateTaskResource resource) {
        var cmd = new UpdateTaskCommand(taskId, resource);
        return this.taskCommandService.handle(cmd);
    }

    @Override
    public void deleteTask(Long taskId) {
        var cmd = new DeleteTaskCommand(taskId);
        this.taskCommandService.handle(cmd);
    }
}
