package com.endockin.patron.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.endockin.patron.model.Authentication;

public interface AuthenticationRepository extends JpaRepository<Authentication, Long> {

  void deleteByKey(String key);

  Authentication findByKey(String key);
}
