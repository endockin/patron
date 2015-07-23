package com.endockin.patron.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.endockin.patron.model.Fleet;
import com.endockin.patron.model.User;

public interface FleetRepository extends JpaRepository<Fleet, Long> {

  List<Fleet> findByUser(User user);

  @Modifying
  @Query("delete from Fleet f where f.name = ?1")
  void removeByName(String name);
}
