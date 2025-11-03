package nrg.inc.synhubbackend.tasks.application;

import nrg.inc.synhubbackend.tasks.interfaces.rest.resources.TaskResource;
import nrg.inc.synhubbackend.tasks.interfaces.rest.resources.UpdateTaskResource;

import java.util.List;

/**
 * Facade abstraction for Task operations used by controllers.
 * Implementations can delegate to the local monolith services (default)
 * or to the remote Tasks microservice (TaskFacadeRemote).
 */
public interface TaskFacade {
    TaskResource getTaskById(Long taskId);
    List<TaskResource> getAllTasksByStatus(String status);
    TaskResource updateTaskStatus(Long taskId, String status);
    TaskResource updateTask(Long taskId, UpdateTaskResource resource);
    void deleteTask(Long taskId);
}
