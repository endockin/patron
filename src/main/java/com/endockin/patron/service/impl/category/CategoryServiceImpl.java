package com.endockin.patron.service.impl.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.endockin.patron.model.Category;
import com.endockin.patron.repository.CategoryRepository;
import com.endockin.patron.service.category.CategoryService;
import com.endockin.patron.service.category.CategoryServiceException;

@Service
public class CategoryServiceImpl implements CategoryService {

  @Autowired
  private CategoryRepository categoryRepo;

  @Override
  public List<Category> findAll() throws CategoryServiceException {
    return categoryRepo.findAll();
  }

  @Override
  public Category find(String key) throws CategoryServiceException {
    return categoryRepo.findByKey(key);
  }

  @Override
  public Category save(Category cat) throws CategoryServiceException {
    return categoryRepo.save(cat);
  }

}
