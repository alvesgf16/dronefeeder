package com.futureh.dronefeeder.controller;

import com.futureh.dronefeeder.model.Video;
import com.futureh.dronefeeder.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/video")
public class VideoController {
  @Autowired
  VideoService service;

  @PostMapping()
  public ResponseEntity<String> saveVideo(@RequestParam("file") MultipartFile file, @RequestParam String descricao) throws IOException {
    service.saveVideo(file, descricao);
    return ResponseEntity.ok("video criado com sucesso, descricao: " + descricao );
  }

  @GetMapping("/{descricao}")
  public ResponseEntity<ByteArrayResource> getVideoById(@PathVariable("id") String descricao) {
    ByteArrayResource byteVideo = new ByteArrayResource(service.getVideoByDescricao(descricao).getData());

    return ResponseEntity.ok(byteVideo);
  }

  @GetMapping()
  public ResponseEntity<List<String>> getAllVideoNames(){
    System.out.print(service.getAll());
    return ResponseEntity
        .ok(service.getAll());
  }
}
