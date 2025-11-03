package nrg.inc.synhubbackend.tasks.application;

import nrg.inc.synhubbackend.tasks.interfaces.rest.resources.TaskResource;
import nrg.inc.synhubbackend.tasks.interfaces.rest.resources.UpdateTaskResource;
import nrg.inc.synhubbackend.tasks.client.TasksHttpClient;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Remote implementation that delegates calls to the Tasks microservice via HTTP.
 * This implementation must be registered conditionally (see TaskFacadeConfig).
 */
@Service
public class TaskFacadeRemote implements TaskFacade {

    private final TasksHttpClient httpClient;

    public TaskFacadeRemote(TasksHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public TaskResource getTaskById(Long taskId) {
        return httpClient.getTask(taskId);
    }

    @Override
    public List<TaskResource> getAllTasksByStatus(String status) {
        return httpClient.getTasksByStatus(status);
    }

    @Override
    public TaskResource updateTaskStatus(Long taskId, String status) {
        return httpClient.updateTaskStatus(taskId, status);
    }

    @Override
    public TaskResource updateTask(Long taskId, UpdateTaskResource resource) {
        return httpClient.updateTask(taskId, resource);
    }

    @Override
    public void deleteTask(Long taskId) {
        httpClient.deleteTask(taskId);
    }
}
