package com.endockin.patron.resource;

import com.endockin.patron.model.Fleet;
import com.endockin.patron.resource.dto.converter.SirenaToFleetConverter;
import com.endockin.patron.resource.dto.SirenaFleetDto;
import com.endockin.patron.service.commandante.CommandanteService;
import com.endockin.patron.service.commandante.CommandanteServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fleet")
public class FleetResource {

    @Autowired
    private CommandanteService commandanteService;

    @Autowired
    private SirenaToFleetConverter sirenaFleetConverter;
    
    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SirenaFleetDto> post(@RequestBody SirenaFleetDto fleet) {
        try {
            Fleet f = commandanteService.launch(sirenaFleetConverter.convert(fleet));
            return null;
        } catch (CommandanteServiceException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
