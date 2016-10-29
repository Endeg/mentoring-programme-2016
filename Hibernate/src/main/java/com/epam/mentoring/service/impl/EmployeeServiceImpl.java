package com.epam.mentoring.service.impl;

import com.epam.mentoring.data.Employee;
import com.epam.mentoring.data.Project;
import com.epam.mentoring.data.Unit;
import com.epam.mentoring.repository.EmployeeRepository;
import com.epam.mentoring.repository.ProjectRepository;
import com.epam.mentoring.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;

/**
 * Created by Endeg on 29.10.2016.
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ProjectRepository projectRepository) {
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public Employee create(Unit unit, Project project) {
        LOG.info("create() - start: unit = {}, project = {}", unit, project);
        final Employee employee = new Employee();
        employee.setUnit(unit);
        employee.setProjects(new HashSet<>());
        employee.getProjects().add(project);
        employeeRepository.save(employee);
        LOG.info("create() - end: employee = {}", employee);
        return employee;
    }

    @Override
    public Employee find(Unit unit, Integer projectId) {
        LOG.info("find() - start: unit = {}, projectId = {}", unit, projectId);
        final Employee employee = employeeRepository.findEmployeeByUnitAndProjectsId(unit, projectId);
        LOG.info("find() - end: employee = {}", employee);
        return employee;
    }

    @Override
    public void delete(Unit unit, Integer projectId) {
        LOG.info("delete() - start: unit = {}, projectId = {}", unit, projectId);
        final Employee employeeToDelete = employeeRepository.findEmployeeByUnitAndProjectsId(unit, projectId);
        employeeRepository.delete(employeeToDelete);
        LOG.info("delete() - end.");
    }

    @Override
    public void update(Employee employee, Unit unit, Integer projectId) {
        LOG.info("update() - start: employee = {}, unit = {}, projectId = {}", employee, unit, projectId);
        employee.getProjects().clear();
        employee.getProjects().add(projectRepository.findOne(projectId));
        employee.setUnit(unit);

        employeeRepository.save(employee);
        LOG.info("update() - end.");
    }
}
