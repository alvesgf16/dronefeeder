package com.futureh.dronefeeder.controller;

import com.futureh.dronefeeder.result.VideoGetAllResult;
import com.futureh.dronefeeder.service.VideoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/video")
public class VideoController {
  @Autowired
  VideoServiceImpl service;

  @PostMapping("/{delivery_id}")
  public ResponseEntity<String> saveVideo(@RequestParam("file") MultipartFile file, @PathVariable("delivery_id") Integer deliveryId ) throws IOException {
    service.saveVideo(file, deliveryId);
    return ResponseEntity.ok("video criado com sucesso"  );
  }

  @GetMapping("/{video_id}")
  public HttpEntity<byte[]> getVideoById(@PathVariable("video_id") Integer id) {
    byte[] byteVideo = service.getVideoById(id).getData();

    HttpHeaders header = new HttpHeaders();

    header.add("Content-Disposition", "attachment;filename=\"delivery.mp4\"");

    HttpEntity<byte[]> result = new HttpEntity<>(byteVideo, header);

    return result;
  }

  @GetMapping()
  public ResponseEntity<List<VideoGetAllResult>> getAllVideoNames(){
    return ResponseEntity
        .ok(service.getAll());
  }
}
