package com.epam.mentoring.service;

import com.epam.mentoring.data.Employee;
import com.epam.mentoring.data.Project;
import com.epam.mentoring.data.Unit;

/**
 * Created by Endeg on 29.10.2016.
 */
public interface EmployeeService {

    Employee create(Unit unit, Project project);

    Employee find(Unit unit, Integer projectId);

    void delete(Unit unit, Integer projectId);

    void update(Employee employee, Unit unit, Integer projectId);

}
