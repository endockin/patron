package com.endockin.patron.service.impl.blueprint;

import com.endockin.patron.model.Blueprint;
import com.endockin.patron.service.blueprint.BlueprintService;
import com.endockin.patron.service.blueprint.BlueprintServiceException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class MemoryBlueprintService implements BlueprintService {

    private final Map<String, Blueprint> blueprints = new HashMap<>();

    @Override
    public List<Blueprint> findAll() throws BlueprintServiceException {
        return new ArrayList<>(blueprints.values());
    }

    @Override
    public Blueprint find(String id) throws BlueprintServiceException {
        if (!blueprints.containsKey(id)) {
            throw new BlueprintServiceException("Blueprint with id [" + id + "] does not exist.");
        }

        return blueprints.get(id);
    }

    @Override
    public Blueprint save(Blueprint b) throws BlueprintServiceException {
        if (blueprints.containsKey(b.getId())) {
            throw new BlueprintServiceException("Blueprint with id [" + b.getId() + "] already exists.");
        }

        blueprints.put(b.getId(), b);
        return b;
    }

}
