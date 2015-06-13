package com.endockin.patron.service.commandante;

import com.endockin.patron.model.Fleet;

public interface CommandanteService {

    Fleet launch(Fleet fleet) throws CommandanteServiceException;

}
