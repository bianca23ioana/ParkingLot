package com.parking.parkinglot.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue
    private Long id;

  @ManyToOne
  @JoinColumn(name = "owner_id")
  private User owner;

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }
  private String licensePlate;
  private String parkingSpot;

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }

  public String getLicensePlate() {
    return licensePlate;
  }

  public void setLicensePlate(String licensePlate) {
    this.licensePlate = licensePlate;
  }

  public String getParkingSpot() {
    return parkingSpot;
  }

  public void setParkingSpot(String parkingSpot) {
    this.parkingSpot = parkingSpot;
  }
}