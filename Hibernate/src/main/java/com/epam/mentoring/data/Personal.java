package com.epam.mentoring.data;

import javax.persistence.Entity;

/**
 * Created by Endeg on 29.10.2016.
 */
@Entity
public class Personal extends Basic {

    private String secrets;

    public String getSecrets() {
        return secrets;
    }

    public void setSecrets(String secrets) {
        this.secrets = secrets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Personal)) return false;
        if (!super.equals(o)) return false;

        Personal personal = (Personal) o;

        return secrets != null ? secrets.equals(personal.secrets) : personal.secrets == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (secrets != null ? secrets.hashCode() : 0);
        return result;
    }
}
