package com.endockin.patron.service.impl.commandante;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.endockin.patron.model.Fleet;
import com.endockin.patron.resource.dto.CommandanteFleetDto;
import com.endockin.patron.service.commandante.CommandanteService;
import com.endockin.patron.service.commandante.CommandanteServiceException;

@Service
public class CommandanteServiceImpl implements CommandanteService {

  private static final Logger LOG = LoggerFactory.getLogger(CommandanteServiceImpl.class);
  private static final String COMMANDANTE_BASE_ADDRESS = "http://localhost:8081";
  // TODO service discovery for address

  @Autowired
  private CommandanteFleetConverter commandanteFleetConverter;

  @Override
  public Fleet launch(Fleet fleet) throws CommandanteServiceException {

    try {
      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<CommandanteFleetDto> result = restTemplate.postForEntity(
          COMMANDANTE_BASE_ADDRESS + Operations.FLEET.getFragment(),
          commandanteFleetConverter.convert(fleet), CommandanteFleetDto.class);

      return commandanteFleetConverter.convert(result.getBody());
    } catch (RestClientException e) {
      LOG.warn("Could not launch fleet.", e);
      handleRestClientException(e);
      return null;
    }
  }

  @Override
  public Fleet find(String fleetId) throws CommandanteServiceException {
    try {
      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<CommandanteFleetDto> result = restTemplate.getForEntity(
          COMMANDANTE_BASE_ADDRESS + Operations.FLEET.getFragment() + "/" + fleetId,
          CommandanteFleetDto.class);
      return commandanteFleetConverter.convert(result.getBody());
    } catch (RestClientException e) {
      LOG.warn("Fleet not found.", e);
      handleRestClientException(e);
      return null;
    }
  }

  public enum Operations {

    FLEET("/api/fleet");

    private final String fragment;

    Operations(String fragment) {
      this.fragment = fragment;
    }

    public String getFragment() {
      return fragment;
    }

    @Override
    public String toString() {
      return getFragment();
    }

  }

  public void handleRestClientException(RestClientException e) throws CommandanteServiceException {
    if (e instanceof HttpStatusCodeException) {
      HttpStatusCodeException hsce = (HttpStatusCodeException) e;

      if (hsce.getStatusCode() == HttpStatus.NOT_FOUND) {
        throw new CommandanteServiceException("Fleet does not exist.",
            CommandanteServiceException.Type.NOT_FOUND);
      } else if (hsce.getStatusCode() == HttpStatus.CONFLICT) {
        throw new CommandanteServiceException("Fleet already exists.",
            CommandanteServiceException.Type.ALREADY_EXISTS);
      }
    }

    throw new CommandanteServiceException(e.getMessage(), CommandanteServiceException.Type.OTHER);
  }

}
