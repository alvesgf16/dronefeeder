package com.futureh.dronefeeder.service;

import com.futureh.dronefeeder.model.Delivery;
import com.futureh.dronefeeder.model.Video;
import com.futureh.dronefeeder.repository.DeliveryRepository;
import com.futureh.dronefeeder.repository.VideoRepository;
import com.futureh.dronefeeder.result.VideoGetAllResult;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VideoService {
  @Autowired
  private VideoRepository videoRepository;

  @Autowired
  private DeliveryRepository deliveryRepository;

  public Video findById(Integer id) {
    return videoRepository.findById(id).orElse(null);
  }

  /**
   * Creates a video for a delivery.
   * 
   */
  @Transactional
  public void create(MultipartFile file, Integer deliveryId) throws IOException {
    Delivery delivery = deliveryRepository.findById(deliveryId).orElse(null);

    Video newVideo = new Video(file.getBytes());

    delivery.setVideo(newVideo);
    videoRepository.save(newVideo);
  }

  /**
   * Lists all the videos.
   * 
   */
  public List<VideoGetAllResult> list() {
    List<String> videoIdsWithDeliveryInfo = videoRepository.getAllEntryNames();

    List<VideoGetAllResult> videosParsed = videoIdsWithDeliveryInfo.stream().map(item -> {
      String[] arrayToFormat = item.split(",");

      return VideoGetAllResult.videoResult(Integer.parseInt(arrayToFormat[0]), arrayToFormat[1],
          Integer.parseInt(arrayToFormat[2]));
    }).collect(Collectors.toList());

    return videosParsed;
  }
}
