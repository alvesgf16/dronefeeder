package com.futureh.dronefeeder.service;

import com.futureh.dronefeeder.model.Video;
import com.futureh.dronefeeder.result.VideoGetAllResult;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface VideoService {

  Video getVideoById(Integer id);

  void saveVideo(MultipartFile file, Integer deliveryId) throws IOException;

  @Query(nativeQuery = true, value = "SELECT delivery.id, delivery.delivery_time, video_id FROM delivery\n" +
      "JOIN video ON delivery.video_id = video.id")
  public List<VideoGetAllResult> getAll();
  }
