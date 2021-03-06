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

      LOG.info("Converting: " + sirenaFleet + ", to: " + f);
      return f;
    } catch (BlueprintServiceException ex) {
      LOG.error("Trouble finding blueprint with name [{}]", sirenaFleet.getBlueprintName());
      return null;
    }
  }

  public SirenaFleetDto convert(Fleet f) {
    SirenaFleetDto dto = new SirenaFleetDto();
    dto.setBlueprintName(f.getBlueprint().getName());
    dto.setCpuPerShip(f.getCpuPerShip());
    dto.setDiskPerShip(f.getDiskSpace());
    dto.setMemoryPerShip(f.getMemoryPerShip());
    dto.setName(f.getName());
    dto.setNumberOfShips(f.getNumberOfShips());
    dto.setUrls(f.getUrls());
    if (f.getStartedAt() != null) {
        dto.setStatus("Started");
        dto.setStatusSince(f.getStartedAt());
    }
    return dto;
  }
}
