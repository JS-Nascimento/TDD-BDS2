package com.devsuperior.bds02.services;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.repositories.EventRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {
    private final EventRepository repository;
    private final CityRepository cityRepository;

    private final ObjectMapper objectMapper;

    public EventService(EventRepository repository, CityRepository cityRepository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.cityRepository = cityRepository;
        this.objectMapper = objectMapper;
    }

    public List<EventDTO> findAll() {
        List<Event> list = repository.findAll(Sort.by("name"));
        return list.stream().map(x -> new EventDTO(x)).collect(Collectors.toList());
    }

    @Transactional
    public EventDTO update(Long id, EventDTO dto) {
        try {
            Event entity = repository.getOne(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new EventDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Produto não encontrado.");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade relacional.");
        }

    }

    private void copyDtoToEntity(EventDTO dto, Event entity) {

        entity.setName(dto.getName());
        entity.setDate(dto.getDate());
        entity.setUrl(dto.getUrl());
        City city = cityRepository.findById(dto.getCityId()).orElse(null);
        if (city != null) {
            entity.setCity(city);
        }


    }

    public Optional<Event> findById(Long id) {
        return repository.findById(id);
    }

}
