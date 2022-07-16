package com.futureh.dronefeeder.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Delivery {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "delivery_time")
  private LocalDateTime deliveryTime;

  private String status;

  @ManyToOne
  @JoinColumn(name = "drone_id")
  private Drone drone;

  /**
   * Construtor da classe.
   *
   */
  public Delivery() {
    this.deliveryTime = LocalDateTime.now();
    this.status = "PENDENTE";
  }

  /**
   * Construtor da classe especificando todos os atributos.
   * 
   * @param id o identificador do episódio
   * @param numero o número do episódio dentro da série
   * @param duracaoEmMinutos a duração, em minutos, do episódio
   * @param serie a série à qual o episódio pertence
   */
  public Delivery(Integer id, LocalDateTime deliveryTime, String status, Drone drone) {
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

  public LocalDateTime getDeliveryTime() {
    return deliveryTime;
  }

  public void setDeliveryTime(LocalDateTime deliveryTime) {
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
}
