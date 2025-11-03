package nrg.inc.synhubbackend.tasks.client;

import nrg.inc.synhubbackend.tasks.interfaces.rest.resources.TaskResource;
import nrg.inc.synhubbackend.tasks.interfaces.rest.resources.UpdateTaskResource;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.Arrays;

/**
 * Lightweight HTTP client for the Tasks microservice.
 * Uses blocking calls for simplicity; you can refactor to non-blocking if needed.
 */
public class TasksHttpClient {

    private final WebClient client;

    public TasksHttpClient(String baseUrl) {
        this.client = WebClient.builder().baseUrl(baseUrl).build();
    }

    public TaskResource getTask(Long id) {
        return client.get()
                .uri("/api/v1/tasks/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(TaskResource.class)
                .block();
    }

    public List<TaskResource> getTasksByStatus(String status) {
        TaskResource[] arr = client.get()
                .uri(uriBuilder -> uriBuilder.path("/api/v1/tasks/status/{status}").build(status))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(TaskResource[].class)
                .block();
        return arr==null? List.of() : Arrays.asList(arr);
    }

    public TaskResource updateTaskStatus(Long id, String status) {
        return client.put()
                .uri("/api/v1/tasks/{id}/status/{status}", id, status)
                .retrieve()
                .bodyToMono(TaskResource.class)
                .block();
    }

    public TaskResource updateTask(Long id, UpdateTaskResource resource) {
        return client.put()
                .uri("/api/v1/tasks/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(resource), UpdateTaskResource.class)
                .retrieve()
                .bodyToMono(TaskResource.class)
                .block();
    }

    public void deleteTask(Long id) {
        client.delete()
                .uri("/api/v1/tasks/{id}", id)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
