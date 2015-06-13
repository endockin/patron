package com.endockin.patron.service.blueprint;

import com.endockin.patron.model.Blueprint;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface BlueprintService {

    List<Blueprint> findAll() throws BlueprintServiceException;

    Blueprint find(String id) throws BlueprintServiceException;

    Blueprint save(Blueprint b) throws BlueprintServiceException;
}
