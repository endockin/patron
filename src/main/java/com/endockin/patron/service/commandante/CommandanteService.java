package com.endockin.patron.service.commandante;

import com.endockin.patron.model.Fleet;
import java.util.List;

public interface CommandanteService {

    Fleet launch(Fleet fleet) throws CommandanteServiceException;

    Fleet find(String fleetId) throws CommandanteServiceException;
    
}
