package com.epam.mentoring.data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

/**
 * Created by Endeg on 29.10.2016.
 */
@Entity
public class Project extends Basic {

    @ManyToMany
    private Set<Employee> employees;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;
        if (!super.equals(o)) return false;

        Project project = (Project) o;

        return employees != null ? employees.equals(project.employees) : project.employees == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (employees != null ? employees.hashCode() : 0);
        return result;
    }
}
