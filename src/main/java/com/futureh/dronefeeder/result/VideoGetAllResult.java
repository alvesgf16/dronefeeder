package com.futureh.dronefeeder.result;

public class VideoGetAllResult {

  private Integer deliveryId;

  private String deliveryTime;

  private Integer videoId;

  /**
   * Método construtor estático da classe.
   *
   */
  public static VideoGetAllResult videoResult(Integer deliveryId, String deliveryTime,
      Integer videoId) {
    VideoGetAllResult result = new VideoGetAllResult();
    result.setDeliveryId(deliveryId);
    result.setDeliveryTime(deliveryTime);
    result.setVideoId(videoId);
    return result;
  }

  public Integer getDeliveryId() {
    return deliveryId;
  }

  public void setDeliveryId(Integer deliveryId) {
    this.deliveryId = deliveryId;
  }

  public String getDeliveryTime() {
    return deliveryTime;
  }

  public void setDeliveryTime(String deliveryTime) {
    this.deliveryTime = deliveryTime;
  }

  public Integer getVideoId() {
    return videoId;
  }

  public void setVideoId(Integer videoId) {
    this.videoId = videoId;
  }
}
