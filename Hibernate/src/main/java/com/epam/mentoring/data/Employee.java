package com.epam.mentoring.data;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

/**
 * Created by Endeg on 29.10.2016.
 */
@Entity
public class Employee extends Basic {

    @Enumerated
    private EmployeeStatus status;

    @OneToOne
    private Personal personal;

    @Embedded
    private Address address;

    @ManyToMany
    private Set<Project> projects;

    @OneToMany
    private Unit unit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        if (!super.equals(o)) return false;

        Employee employee = (Employee) o;

        if (status != employee.status) return false;
        if (personal != null ? !personal.equals(employee.personal) : employee.personal != null) return false;
        if (address != null ? !address.equals(employee.address) : employee.address != null) return false;
        if (projects != null ? !projects.equals(employee.projects) : employee.projects != null) return false;
        return unit != null ? unit.equals(employee.unit) : employee.unit == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (personal != null ? personal.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (projects != null ? projects.hashCode() : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        return result;
    }
}
