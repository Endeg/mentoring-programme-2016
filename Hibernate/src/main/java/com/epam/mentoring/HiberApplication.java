package com.epam.mentoring;

import com.epam.mentoring.data.Employee;
import com.epam.mentoring.data.Project;
import com.epam.mentoring.data.Unit;
import com.epam.mentoring.repository.ProjectRepository;
import com.epam.mentoring.repository.UnitRepository;
import com.epam.mentoring.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class HiberApplication {

    private static final Logger LOG = LoggerFactory.getLogger(HiberApplication.class);

    public static void main(String[] args) {
        final ConfigurableApplicationContext context = SpringApplication.run(HiberApplication.class, args);

        final ProjectRepository projectRepository = context.getBean(ProjectRepository.class);
        final UnitRepository unitRepository = context.getBean(UnitRepository.class);
        final EmployeeService employeeService = context.getBean(EmployeeService.class);

        final Project projectOne = projectRepository.save(new Project());
        final Unit unitOne = unitRepository.save(new Unit());

        final Employee employeeOne = employeeService.create(unitOne, projectOne);

        LOG.info("employeeOne = {}", employeeOne);

        context.close();
    }
}
