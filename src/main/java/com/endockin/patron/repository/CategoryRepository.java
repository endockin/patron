package com.endockin.patron.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.endockin.patron.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

  Category findByKey(String key);
}
