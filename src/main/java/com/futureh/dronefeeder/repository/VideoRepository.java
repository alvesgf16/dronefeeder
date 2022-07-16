package com.futureh.dronefeeder.repository;

import com.futureh.dronefeeder.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer> {
  Video findByDescricao(String name);

  @Query(nativeQuery = true, value="SELECT descricao, id FROM video")
  List<String> getAllEntryNames();
}
