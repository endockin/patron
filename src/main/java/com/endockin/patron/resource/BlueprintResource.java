package com.endockin.patron.resource;

import com.endockin.patron.model.Blueprint;
import com.endockin.patron.service.blueprint.BlueprintService;
import com.endockin.patron.service.blueprint.BlueprintServiceException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/blueprint")
public class BlueprintResource {

    @Autowired
    private BlueprintService blueprintService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Blueprint>> getAll() {
        try {
            List<Blueprint> blueprints = blueprintService.findAll();

            return new ResponseEntity<>(blueprints, HttpStatus.OK);
        } catch (BlueprintServiceException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Blueprint> get(@PathVariable String id) {
        try {
            Blueprint blueprint = blueprintService.find(id);
            
            return new ResponseEntity<>(blueprint, HttpStatus.OK);
        } catch (BlueprintServiceException ex) {
            if (BlueprintServiceException.Type.NOT_FOUND.equals(ex.getType())) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
