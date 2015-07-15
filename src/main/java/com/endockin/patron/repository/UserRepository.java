package com.endockin.patron.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.endockin.patron.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

  User findByEmail(String email);
}
