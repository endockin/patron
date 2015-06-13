package com.endockin.patron.service.impl.commandante;

import com.endockin.patron.model.Fleet;
import com.endockin.patron.resource.dto.CommandanteFleetDto;
import org.springframework.stereotype.Component;

@Component
public class CommandanteFleetConverter {

    public CommandanteFleetDto conert(Fleet fleet) {
        CommandanteFleetDto dto = new CommandanteFleetDto();
        dto.setCpu(new Integer(fleet.getCpuPerShip()).doubleValue());
        dto.setId(fleet.getName());
        dto.setImage(fleet.getBlueprint().getId());
        dto.setInstanceNumber(fleet.getNumberOfShips());
        dto.setMemory(new Integer(fleet.getMemoryPerShip()).doubleValue());
        return dto;
    }

}
