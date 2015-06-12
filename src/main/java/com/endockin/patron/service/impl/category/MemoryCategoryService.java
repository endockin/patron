package com.endockin.patron.service.impl.category;

import com.endockin.patron.model.Blueprint;
import com.endockin.patron.model.Category;
import com.endockin.patron.service.blueprint.BlueprintService;
import com.endockin.patron.service.category.CategoryService;
import com.endockin.patron.service.category.CategoryServiceException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemoryCategoryService implements CategoryService {

    private static final Logger LOG = LoggerFactory.getLogger(MemoryCategoryService.class);

    @Autowired
    private BlueprintService blueprintService;

    private final Map<String, Category> categories = new HashMap<>();

    public MemoryCategoryService() {
        initializeMap();
    }

    @Override
    public List<Category> findAll() throws CategoryServiceException {
        return new ArrayList<>(categories.values());
    }

    @Override
    public Category find(String id) throws CategoryServiceException {
        if (!categories.containsKey(id)) {
            throw new CategoryServiceException("Category with id [" + id + "] does not exist");
        }

        return categories.get(id);
    }

    private void initializeMap() {
        LOG.info("Initializing categories");

        //OS
        Category operatingSystems = new Category();
        operatingSystems.setId("os");
        operatingSystems.setName("Operating Systems");

        Blueprint ubuntu = new Blueprint();
        ubuntu.setDescription("Ubuntu Operating System Blueprint");
        ubuntu.setId("ubuntu");
        ubuntu.setLogo("logo.png");
        ubuntu.setName("Ubuntu");

        Blueprint debian = new Blueprint();
        debian.setDescription("Debian Operating System Blueprint");
        debian.setId("debian");
        debian.setLogo("logo.png");
        debian.setName("Debian");

        operatingSystems.setBlueprints(new ArrayList<>(Arrays.asList(ubuntu, debian)));
        categories.put(operatingSystems.getId(), operatingSystems);

    }
}
