package com.github.carniwar.springboot.scalable.rest.controller;

import com.github.carniwar.springboot.scalable.api.dto.DiffResultDTO;
import com.github.carniwar.springboot.scalable.api.endpoint.DiffEndpoint;
import com.github.carniwar.springboot.scalable.api.util.Roles;
import com.github.carniwar.springboot.scalable.core.service.DiffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

/**
 * Rest controller to expose Diff operations. This methods should be accessed by an user with <code>Roles.USER</code> role associated.
 */
@RestController
@RequestMapping(
        value = DiffEndpoint.PATH_V1,
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RolesAllowed(Roles.USER)
public class DiffController implements DiffEndpoint {

    private DiffService diffService;

    @Autowired
    public DiffController(DiffService diffService) {
        this.diffService = diffService;
    }

    @PostMapping(value = "/right", consumes = MediaType.TEXT_PLAIN_VALUE)
    @Override
    public ResponseEntity<String> saveRight(@PathVariable String id, @RequestBody String data) {
        diffService.saveRight(id, data);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/left", consumes = MediaType.TEXT_PLAIN_VALUE)
    @Override
    public ResponseEntity<String> saveLeft(@PathVariable String id, @RequestBody String data) {
        diffService.saveLeft(id, data);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Override
    public ResponseEntity<DiffResultDTO> compare(@PathVariable String id) {
        DiffResultDTO result = diffService.compare(id);
        return ResponseEntity.ok(result);
    }

}
