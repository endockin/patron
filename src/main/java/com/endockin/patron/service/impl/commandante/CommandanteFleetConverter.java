package com.endockin.patron.service.impl.commandante;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.endockin.patron.model.Fleet;
import com.endockin.patron.resource.dto.CommandanteFleetDto;
import com.endockin.patron.service.blueprint.BlueprintService;
import com.endockin.patron.service.blueprint.BlueprintServiceException;

@Component
public class CommandanteFleetConverter {

  private static final Logger LOG = LoggerFactory.getLogger(CommandanteFleetConverter.class);

  @Autowired
  private BlueprintService blueprintService;

  public CommandanteFleetDto convert(Fleet fleet) {
    CommandanteFleetDto dto = new CommandanteFleetDto();
    dto.setCpu(new Integer(fleet.getCpuPerShip()).doubleValue());
    dto.setId(fleet.getName());
    dto.setImage(fleet.getBlueprint().getImageName());
    dto.setInstanceNumber(fleet.getNumberOfShips());
    dto.setMemory(new Integer(fleet.getMemoryPerShip()).doubleValue());
    dto.setPorts(fleet.getBlueprint().getPorts());
    return dto;
  }

  public Fleet convert(CommandanteFleetDto dto) {
    try {
      Fleet f = new Fleet();
      f.setBlueprint(blueprintService.find(dto.getImage()));
      f.setCpuPerShip(dto.getCpu().intValue());
      f.setMemoryPerShip(dto.getMemory().intValue());
      f.setName(dto.getId());
      f.setNumberOfShips(dto.getInstanceNumber());

      List<String> urls = new ArrayList<>();
      dto.getShips().stream().forEach((shipDto) -> {
        urls.add(shipDto.getHost() + ":" + shipDto.getPorts().get(0));
      });
      f.setUrls(urls);

      return f;
    } catch (BlueprintServiceException ex) {
      LOG.warn("Unknown blueprint [{}]", dto.getImage());
      return null;
    }
  }

}
