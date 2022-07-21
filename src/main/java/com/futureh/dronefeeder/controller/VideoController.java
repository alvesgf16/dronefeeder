package com.futureh.dronefeeder.controller;

import com.futureh.dronefeeder.result.VideoGetAllResult;
import com.futureh.dronefeeder.service.VideoService;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("videos")
public class VideoController {
  @Autowired
  VideoService service;

  @PostMapping("{delivery_id}")
  public ResponseEntity<String> create(@RequestParam("file") MultipartFile file,
      @PathVariable("delivery_id") Integer deliveryId) throws IOException {
    service.create(file, deliveryId);
    return ResponseEntity.ok("Video created successfully");
  }

  /**
   * Finds a video by its id.
   * 
   */
  @GetMapping("{video_id}")
  public HttpEntity<byte[]> findById(@PathVariable("video_id") Integer id) {
    byte[] byteVideo = service.findById(id).getData();

    HttpHeaders header = new HttpHeaders();

    header.add("Content-Disposition", "attachment;filename=\"delivery.mp4\"");

    HttpEntity<byte[]> result = new HttpEntity<>(byteVideo, header);

    return result;
  }

  @GetMapping()
  public ResponseEntity<List<VideoGetAllResult>> list() {
    return ResponseEntity.ok(service.list());
  }
}
