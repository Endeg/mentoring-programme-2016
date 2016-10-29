package com.epam.mentoring.data;

import javax.persistence.Embeddable;

/**
 * Created by Endeg on 29.10.2016.
 */
@Embeddable
public class Address extends Basic {

    private String street;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        if (!super.equals(o)) return false;

        Address address = (Address) o;

        return street != null ? street.equals(address.street) : address.street == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (street != null ? street.hashCode() : 0);
        return result;
    }
}
