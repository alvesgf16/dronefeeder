package com.futureh.dronefeeder.model;

import javax.persistence.*;

@Entity
public class Video {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(unique = true)
  private String descricao;

  @OneToOne(mappedBy = "video", cascade = CascadeType.ALL)
  private Delivery delivery;

  @Lob
  private byte[] data;

  public Video(byte[] data, String descricao) {
    this.data = data;
    this.descricao = descricao;
  }

  public Video() {

  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public byte[] getData() {
    return data;
  }

  public void setData(byte[] data) {
    this.data = data;
  }
}

