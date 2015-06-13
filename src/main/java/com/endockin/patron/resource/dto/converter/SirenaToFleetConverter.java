package com.endockin.patron.resource.dto.converter;

import com.endockin.patron.model.Fleet;
import com.endockin.patron.resource.dto.SirenaFleetDto;
import com.endockin.patron.service.blueprint.BlueprintService;
import com.endockin.patron.service.blueprint.BlueprintServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SirenaToFleetConverter {

    private static final Logger LOG = LoggerFactory.getLogger(SirenaToFleetConverter.class);

    @Autowired
    private BlueprintService blueprintService;

    public Fleet convert(SirenaFleetDto sirenaFleet) {
        try {
            Fleet f = new Fleet();
            f.setBlueprint(blueprintService.find(sirenaFleet.getBlueprintName()));
            f.setCpuPerShip(sirenaFleet.getCpuPerShip());
            f.setDiskSpace(sirenaFleet.getDiskPerShip());
            f.setMemoryPerShip(sirenaFleet.getMemoryPerShip());
            f.setName(sirenaFleet.getName());
            f.setNumberOfShips(sirenaFleet.getNumberOfShips());
            return f;
        } catch (BlueprintServiceException ex) {
            LOG.error("Trouble finding blueprint with name [{}]", sirenaFleet.getBlueprintName());
            return null;
        }
    }
}
