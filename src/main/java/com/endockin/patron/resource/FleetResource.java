package com.endockin.patron.resource;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.endockin.patron.filter.SecurityFilter;
import com.endockin.patron.model.Fleet;
import com.endockin.patron.repository.FleetRepository;
import com.endockin.patron.resource.dto.SirenaFleetDto;
import com.endockin.patron.resource.dto.converter.SirenaToFleetConverter;
import com.endockin.patron.service.commandante.CommandanteService;
import com.endockin.patron.service.commandante.CommandanteServiceException;

@RestController
@RequestMapping("/api/fleet")
public class FleetResource {

  private static final Logger LOG = LoggerFactory.getLogger(FleetResource.class);

  @Autowired
  private CommandanteService commandanteService;

  @Autowired
  private SirenaToFleetConverter sirenaFleetConverter;

  @Autowired
  private FleetRepository fleetRepo;

  @Autowired
  private SecurityFilter securityFilter;

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<SirenaFleetDto> post(@RequestBody SirenaFleetDto fleet) {
    try {
      Fleet f = commandanteService.launch(sirenaFleetConverter.convert(fleet));

      this.fleetRepo.save(f);

      return new ResponseEntity<>(sirenaFleetConverter.convert(f), HttpStatus.OK);
    } catch (CommandanteServiceException ex) {
      if (CommandanteServiceException.Type.ALREADY_EXISTS.equals(ex.getType())) {
        return new ResponseEntity<>(HttpStatus.CONFLICT);
      }

      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<SirenaFleetDto> get(@PathVariable String id) {
    try {
      Fleet f = commandanteService.find(id);
      return new ResponseEntity<>(sirenaFleetConverter.convert(f), HttpStatus.OK);
    } catch (CommandanteServiceException ex) {
      if (CommandanteServiceException.Type.NOT_FOUND.equals(ex.getType())) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<SirenaFleetDto>> get() {
    List<SirenaFleetDto> resultList = new ArrayList<>();
    List<Fleet> fleets = this.fleetRepo.findByUser(securityFilter.getAuthentication().getUser());

    for (Fleet fleet : fleets) {
      try {
        Fleet f = commandanteService.find(fleet.getName());
        resultList.add(sirenaFleetConverter.convert(f));
      } catch (CommandanteServiceException ex) {
        LOG.warn("Error retrieving fleet.");
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    return new ResponseEntity<>(resultList, HttpStatus.OK);
  }

}
