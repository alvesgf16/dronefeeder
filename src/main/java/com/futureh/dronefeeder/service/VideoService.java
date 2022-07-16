package com.futureh.dronefeeder.service;

import com.futureh.dronefeeder.model.Video;
import com.futureh.dronefeeder.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class VideoService {
  @Autowired
  private VideoRepository repository;

  public Video getVideoByDescricao(String descricao) {
    return repository.findByDescricao(descricao);
  }

  public void saveVideo(MultipartFile file, String descricao) throws IOException {
    Video newVideo = new Video(file.getBytes(), descricao);
    repository.save(newVideo);
  }

  public List<String> getAll() {
    return repository.getAllEntryNames();
  }
}
