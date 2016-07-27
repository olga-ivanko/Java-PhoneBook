/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebook.db.entity;

import java.util.Objects;

/**
 *
 * @author homefulloflove
 */
public class Contact {

    private Integer id;
    private String fullName;
    private String phoneNum;
    private String email;
    private String skype;
    private String image;

    public Contact() {
    }

    public Contact(String fullName, String phoneNum, String email, String skype) {
        this.fullName = fullName;
        this.phoneNum = phoneNum;
        this.email = email;
        this.skype = skype;
    }

    public Contact(Integer id, String fullName, String phoneNum, String email, String skype, String image) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNum = phoneNum;
        this.email = email;
        this.skype = skype;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
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
        final Contact other = (Contact) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Contact{" + "id=" + id + ", fullName=" + fullName + '}';
    }

}
