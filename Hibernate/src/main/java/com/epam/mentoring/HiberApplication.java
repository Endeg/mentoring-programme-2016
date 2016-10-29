package com.epam.mentoring;

import com.epam.mentoring.data.Project;
import com.epam.mentoring.repository.ProjectRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class HiberApplication {

    public static void main(String[] args) {
        final ConfigurableApplicationContext context = SpringApplication.run(HiberApplication.class, args);

        final ProjectRepository projectRepository = context.getBean(ProjectRepository.class);

        final Project project = new Project();

        System.out.println("Project = " + project);

        final Project savedProject = projectRepository.save(project);
        System.out.println("SavedProject = " + savedProject);

        context.close();
    }
}
