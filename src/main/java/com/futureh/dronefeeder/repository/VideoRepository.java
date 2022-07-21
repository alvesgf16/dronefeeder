package com.futureh.dronefeeder.repository;

import com.futureh.dronefeeder.model.Video;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer> {
  @Query(nativeQuery = true,
      value = "SELECT delivery.id, delivery.delivery_time, video_id FROM delivery\n"
          + "JOIN video ON delivery.video_id = video.id")
  List<String> getAllEntryNames();
}
