package com.futureh.dronefeeder.service;

import com.futureh.dronefeeder.model.Delivery;
import com.futureh.dronefeeder.model.Video;
import com.futureh.dronefeeder.repository.DeliveryRepository;
import com.futureh.dronefeeder.repository.VideoRepository;
import com.futureh.dronefeeder.result.VideoGetAllResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoServiceImpl implements VideoService {
  @Autowired
  private VideoRepository repository;

  @Autowired
  private DeliveryRepository deliveryRepository;

  @Override
  public Video getVideoById(Integer id) {
    return repository.findById(id).orElse(null);
  }

  @Override
  @Transactional
  public void saveVideo(MultipartFile file, Integer deliveryId) throws IOException {
    Delivery delivery = deliveryRepository.findById(deliveryId).orElse(null);

    Video video = delivery.getVideo();

    Video newVideo = new Video(file.getBytes());

    delivery.setVideo(newVideo);
    repository.save(newVideo);
  }

  @Override
  public List<VideoGetAllResult> getAll() {
    List<String> videoIdsWithDeliveryInfo = repository.getAllEntryNames();

    List<VideoGetAllResult> videosParsed = videoIdsWithDeliveryInfo.stream().map((item) -> {
      String[] arrayToFormat = item.split(",");
      return VideoGetAllResult.VideoResult(
          Integer.parseInt(arrayToFormat[0]),
          arrayToFormat[1],
          Integer.parseInt(arrayToFormat[2])
      );
    }).collect(Collectors.toList());

    return videosParsed;
  }
}
