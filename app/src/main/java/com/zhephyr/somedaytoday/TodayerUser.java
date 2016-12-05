package com.zhephyr.somedaytoday;

import java.util.Date;

public class TodayerUser {
    private String id;
    private String fName;
    private String lName;
    private String createDate;
    private String location;
    private String gender;
    private String email;
    private String phoneNumber;
    private Date   birthDate;

    public TodayerUser() {}

    public TodayerUser(String id, String fName, String lName, String createDate, String location,
                       String gender, String email, String phoneNumber, Date birthDate) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.createDate = createDate;
        this.location = location;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
