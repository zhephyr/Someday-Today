package com.zhephyr.somedaytoday;

import android.location.Location;
import android.provider.ContactsContract;

import java.util.Date;

public class User {
    private String fName;
    private String lName;
    private Date createDate;
    private Location location;
    private String email;
//    private ContactsContract.CommonDataKinds.Phone phoneNum = null;
//    private Date birthDate = null;

    public User(Date birthDate, String fName, String lName, Date createDate, Location location, String email, ContactsContract.CommonDataKinds.Phone phoneNum) {
        this.fName = fName;
        this.lName = lName;
        this.createDate = createDate;
        this.location = location;
        this.email = email;
//        this.phoneNum = phoneNum;
//        this.birthDate = birthDate;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public ContactsContract.CommonDataKinds.Phone getPhoneNum() {
//        return phoneNum;
//    }
//
//    public void setPhoneNum(ContactsContract.CommonDataKinds.Phone phoneNum) {
//        this.phoneNum = phoneNum;
//    }
//
//    public Date getBirthDate() {
//        return birthDate;
//    }
//
//    public void setBirthDate(Date birthDate) {
//        this.birthDate = birthDate;
//    }
}
