package com.endockin.patron.service.impl.blueprint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.endockin.patron.model.Blueprint;
import com.endockin.patron.repository.BlueprintRepository;
import com.endockin.patron.service.blueprint.BlueprintService;
import com.endockin.patron.service.blueprint.BlueprintServiceException;

@Service
public class BlueprintServiceImpl implements BlueprintService {

  @Autowired
  private BlueprintRepository blueprintRepo;

  @Override
  public List<Blueprint> findAll() throws BlueprintServiceException {
    return blueprintRepo.findAll();
  }

  @Override
  public Blueprint find(String imageName) throws BlueprintServiceException {
    return blueprintRepo.findByImageName(imageName);
  }

  @Override
  public Blueprint save(Blueprint b) throws BlueprintServiceException {
    return blueprintRepo.save(b);
  }

}
