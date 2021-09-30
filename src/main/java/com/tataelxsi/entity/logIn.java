package com.tataelxsi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class logIn {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer userId;
@Column(name="password")
private String password;
@Column(name="user_name")
private String userName;
@Column
private String email;
@Column
private String phoneNumber;
@Column
private String address;

 public Integer getUserId() {
return userId;
}

 public void setUserId(Integer userId) {
this.userId = userId;
}

 public String getUserName() {
return userName;
}

 public void setUserName(String userName) {
this.userName = userName;
}

 public String getPassword() {
return password;
}

 public void setPassword(String password) {
this.password = password;
}

 public logIn(Integer userId, String userName, String password) {
super();
this.userId = userId;
this.userName = userName;
this.password = password;
}


 public logIn(Integer userId, String password, String userName, String email, String phoneNumber, String address) {
super();
this.userId = userId;
this.password = password;
this.userName = userName;
this.email = email;
this.phoneNumber = phoneNumber;
this.address = address;
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

 public String getAddress() {
return address;
}

 public void setAddress(String address) {
this.address = address;
}

 public logIn(String userName, String password) {
super();
this.userName = userName;
this.password = password;
}

 public logIn() {
super();
// TODO Auto-generated constructor stub
}




}
