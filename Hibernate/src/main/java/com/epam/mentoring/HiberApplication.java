package com.epam.mentoring;

import com.epam.mentoring.data.Employee;
import com.epam.mentoring.data.Project;
import com.epam.mentoring.data.Unit;
import com.epam.mentoring.repository.ProjectRepository;
import com.epam.mentoring.repository.UnitRepository;
import com.epam.mentoring.service.EmployeeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class HiberApplication {

    public static void main(String[] args) {
        final ConfigurableApplicationContext context = SpringApplication.run(HiberApplication.class, args);

        final ProjectRepository projectRepository = context.getBean(ProjectRepository.class);
        final UnitRepository unitRepository = context.getBean(UnitRepository.class);
        final EmployeeService employeeService = context.getBean(EmployeeService.class);

        createProjectUnitEmployeeThenDeleteHim(projectRepository, unitRepository, employeeService);
        createThenUpdateEmployeeWithOtherProjectAndUnit(projectRepository, unitRepository, employeeService);

        context.close();
    }

    private static void createThenUpdateEmployeeWithOtherProjectAndUnit(ProjectRepository projectRepository, UnitRepository unitRepository, EmployeeService employeeService) {
        final Project projectOne = projectRepository.save(new Project());
        final Unit unitOne = unitRepository.save(new Unit());

        final Project projectTwo = projectRepository.save(new Project());
        final Unit unitTwo = unitRepository.save(new Unit());

        final Employee employeeOne = employeeService.create(unitOne, projectOne);

        employeeService.update(employeeOne, unitTwo, projectTwo.getId());

        final Employee updatedEmployee = employeeService.find(unitTwo, projectTwo.getId());
        final Employee notFoundEmployee = employeeService.find(unitOne, projectOne.getId());
    }

    private static void createProjectUnitEmployeeThenDeleteHim(ProjectRepository projectRepository, UnitRepository unitRepository, EmployeeService employeeService) {
        final Project projectOne = projectRepository.save(new Project());
        final Unit unitOne = unitRepository.save(new Unit());

        final Employee employeeOne = employeeService.create(unitOne, projectOne);

        final Employee employeeFoundByFindMethod = employeeService.find(unitOne, projectOne.getId());

        employeeService.delete(unitOne, projectOne.getId());

        final Employee employeeFoundByFindMethodAfterDeletion = employeeService.find(unitOne, projectOne.getId());
    }
}
