package com.parking.parkinglot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
//@Table(name = "user_group")
public class UserGroup {
  private Long id;
  private String username;
  private String userGroup;

  @Id
  @GeneratedValue
  public Long getId() {return id;}
  public void setId(Long id) {this.id = id;}
  public String getUsername() {return username;}
  public void setUsername(String username) {this.username = username;}
  public String getUserGroup() {return userGroup;}
  public void setUserGroup(String userGroup) {this.userGroup = userGroup;}
}