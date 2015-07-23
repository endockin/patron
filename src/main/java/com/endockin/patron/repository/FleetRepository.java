package com.endockin.patron.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.endockin.patron.model.Fleet;
import com.endockin.patron.model.User;

public interface FleetRepository extends JpaRepository<Fleet, Long> {

  List<Fleet> findByUser(User user);
  
  void deleteByName(String name);
}
