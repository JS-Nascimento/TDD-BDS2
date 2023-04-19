package com.devsuperior.bds02.controllers;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.services.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CityController {

    private final CityService service;

    public CityController(CityService service) {
        this.service = service;
    }

    @GetMapping("cities")
    public ResponseEntity<List<CityDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("cities")
    public ResponseEntity<City> create(@RequestBody City city) {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(city));
    }

    @DeleteMapping(value = "/cities/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {

        service.delete(id);

    }
}