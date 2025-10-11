package nrg.inc.synhubbackend.tasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "nrg.inc.synhubbackend.tasks")
public class TasksApplication {
    public static void main(String[] args) {
        SpringApplication.run(TasksApplication.class, args);
    }
}
