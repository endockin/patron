package com.endockin.patron.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.endockin.patron.model.Blueprint;

public interface BlueprintRepository extends JpaRepository<Blueprint, Long> {

  Blueprint findByImageName(String imageName);
}
