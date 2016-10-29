package com.epam.mentoring.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Endeg on 29.10.2016.
 */
@Entity
public class Personal {

    @Id
    @GeneratedValue
    private Integer id;

    private String secrets;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSecrets() {
        return secrets;
    }

    public void setSecrets(String secrets) {
        this.secrets = secrets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Personal personal = (Personal) o;

        if (id != null ? !id.equals(personal.id) : personal.id != null) return false;
        return secrets != null ? secrets.equals(personal.secrets) : personal.secrets == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (secrets != null ? secrets.hashCode() : 0);
        return result;
    }
}
