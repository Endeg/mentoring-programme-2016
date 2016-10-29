package com.epam.mentoring.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * Created by Endeg on 29.10.2016.
 */
@Entity
public class Unit {

    @Id
    @GeneratedValue
    private Integer id;

    @OneToMany
    private Set<Employee> employees;

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Unit unit = (Unit) o;

        return id != null ? id.equals(unit.id) : unit.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "id=" + id +
                ", employees.size=" + (employees != null ? employees.size() : null) +
                '}';
    }
}
