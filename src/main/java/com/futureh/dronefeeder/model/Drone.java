package com.futureh.dronefeeder.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Drone {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String latitude;

  private String longitude;

  @OneToMany(mappedBy = "drone", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Delivery> deliveries = new ArrayList<Delivery>();

  /**
   * Construtor da classe.
   */
  public Drone() {}

  /**
   * Construtor da classe especificando os atributos necessários para criação de uma entidade.
   * 
   */
  public Drone(String latitude, String longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public List<Delivery> getDeliveries() {
    return deliveries;
  }

  public void setDeliveries(List<Delivery> deliveries) {
    this.deliveries = deliveries;
  }

  public void addDelivery(Delivery delivery) {
    deliveries.add(delivery);
  }
}
