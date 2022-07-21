package com.futureh.dronefeeder.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Delivery {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "delivery_time")
  private String deliveryTime;

  private String status;

  @ManyToOne
  @JoinColumn(name = "drone_id")
  @JsonBackReference
  private Drone drone;

  @JoinColumn(name = "video_id")
  @OneToOne
  @JsonIgnore
  private Video video;

  /**
   * Construtor da classe.
   *
   */
  public Delivery() {
    this.deliveryTime = LocalDateTime.now().toString();
    this.status = "PENDENTE";
  }

  /**
   * Construtor da classe especificando todos os atributos.
   * 
   */
  public Delivery(Integer id, String deliveryTime, String status, Drone drone) {
    this.id = id;
    this.deliveryTime = deliveryTime;
    this.status = status;
    this.drone = drone;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDeliveryTime() {
    return deliveryTime;
  }

  public void setDeliveryTime(String deliveryTime) {
    this.deliveryTime = deliveryTime;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void setDrone(Drone drone) {
    this.drone = drone;
  }

  public Drone getDrone() {
    return drone;
  }

  public Video getVideo() {
    return video;
  }

  public void setVideo(Video video) {
    this.video = video;
  }
}
