package com.epam.mentoring.service.impl;

import com.epam.mentoring.data.Employee;
import com.epam.mentoring.data.Project;
import com.epam.mentoring.data.Unit;
import com.epam.mentoring.repository.EmployeeRepository;
import com.epam.mentoring.service.EmployeeService;
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

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee create(Unit unit, Project project) {
        final Employee employee = new Employee();
        employee.setUnit(unit);
        employee.setProjects(new HashSet<>());
        employee.getProjects().add(project);
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Employee find(Unit unit, Integer projectId) {
        return null;
    }

    @Override
    public void delete(Unit unit, Integer projectId) {

    }

    @Override
    public void update(Unit unit, Integer projectId) {

    }
}
