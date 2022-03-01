package com.example.userinfo;

import java.io.Serializable;

public class UserDetails implements Serializable {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String gender;
    private String password;

    UserDetails() {}
    UserDetails(String firstName, String lastName, String email, String userName,String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        UserDetails that = (UserDetails) o;
        return firstName.equals(that.firstName) &&  lastName.equals(that.lastName) &&  userName.equals(that.userName) && email.equals(that.email) &&
                gender.equals(that.gender) && password.equals(that.password);
    }

}
