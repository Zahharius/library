/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author admin
 */
public class Reader implements Serializable{
     private String firstname;
    private String lastname;
    private String phone;

    public Reader() {
    }

    public Reader(String firstname, String lastname, String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.firstname);
        hash = 47 * hash + Objects.hashCode(this.lastname);
        hash = 47 * hash + Objects.hashCode(this.phone);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reader other = (Reader) obj;
        if (!Objects.equals(this.firstname, other.firstname)) {
            return false;
        }
        if (!Objects.equals(this.lastname, other.lastname)) {
            return false;
        }
        return Objects.equals(this.phone, other.phone);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Reader{");
        sb.append("firstname=").append(firstname);
        sb.append(", lastname=").append(lastname);
        sb.append(", phone=").append(phone);
        sb.append('}');
        return sb.toString();
    }
    
    
}