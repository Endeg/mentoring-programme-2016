package com.epam.mentoring.data;

import javax.persistence.Id;

/**
 * Created by Endeg on 29.10.2016.
 */
public abstract class Basic {

    @Id
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Basic basic = (Basic) o;

        return id != null ? id.equals(basic.id) : basic.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
