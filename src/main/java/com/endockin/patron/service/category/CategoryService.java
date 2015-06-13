package com.endockin.patron.service.category;

import com.endockin.patron.model.Category;
import java.util.List;

public interface CategoryService {

    List<Category> findAll() throws CategoryServiceException;

    Category find(String id) throws CategoryServiceException;
}
