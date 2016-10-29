package com.epam.mentoring.data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Set;

/**
 * Created by Endeg on 29.10.2016.
 */
@Entity
public class Unit extends Basic {

    @ManyToOne
    private Set<Employee> employees;

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Unit)) return false;
        if (!super.equals(o)) return false;

        Unit unit = (Unit) o;

        return employees != null ? employees.equals(unit.employees) : unit.employees == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (employees != null ? employees.hashCode() : 0);
        return result;
    }
}
