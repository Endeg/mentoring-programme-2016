package com.epam.mentoring.repository;

import com.epam.mentoring.data.Employee;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Endeg on 29.10.2016.
 */
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
}
