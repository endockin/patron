package com.endockin.patron.service.impl.commandante;

import com.endockin.patron.model.Fleet;
import com.endockin.patron.service.commandante.CommandanteService;
import com.endockin.patron.service.commandante.CommandanteServiceException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CommandanteServiceImpl implements CommandanteService {

    private static final String COMMANDANTE_BASE_ADDRESS = "http://localhost:8080";
    //TODO service discovery for address
    
    @Override
    public Fleet launch(Fleet fleet) throws CommandanteServiceException {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(COMMANDANTE_BASE_ADDRESS + Operations.FLEET.getFragment(), this, Fleet.class);
        
        return null;
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
}
