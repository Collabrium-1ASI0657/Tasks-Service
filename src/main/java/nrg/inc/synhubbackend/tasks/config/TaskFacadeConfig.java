package nrg.inc.synhubbackend.tasks.config;

import nrg.inc.synhubbackend.tasks.application.TaskFacade;
import nrg.inc.synhubbackend.tasks.application.TaskFacadeRemote;
import nrg.inc.synhubbackend.tasks.application.TaskFacadeLocal;
import nrg.inc.synhubbackend.tasks.client.TasksHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskFacadeConfig {

    @Value("${tasks.service.base-url:http://localhost:8081}")
    private String tasksServiceBaseUrl;

    @Bean
    @ConditionalOnProperty(name = "tasks.backend", havingValue = "service")
    public TasksHttpClient tasksHttpClient() {
        return new TasksHttpClient(tasksServiceBaseUrl);
    }

    // When tasks.backend=service, use remote facade
    @Bean
    @ConditionalOnProperty(name = "tasks.backend", havingValue = "service")
    public TaskFacade taskFacadeRemote(TasksHttpClient client) {
        return new TaskFacadeRemote(client);
    }

    // Default: use local facade (monolith behavior)
    @Bean
    @ConditionalOnProperty(name = "tasks.backend", havingValue = "monolith", matchIfMissing = true)
    public TaskFacade taskFacadeLocal(TaskFacadeLocal local) {
        return local;
    }
}
