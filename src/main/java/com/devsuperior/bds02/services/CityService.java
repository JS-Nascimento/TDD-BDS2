package com.devsuperior.bds02.services;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.CityRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityService {
    private final CityRepository repository;

    public CityService(CityRepository repository) {
        this.repository = repository;
    }

    public List<CityDTO> findAll() {
        List<City> list = repository.findAll(Sort.by("name"));
        return list.stream().map(x -> new CityDTO(x)).collect(Collectors.toList());
    }

    public City create(City city) {
        return repository.save(city);
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

    public Optional<City> findById(Long id) {
        return repository.findById(id);
    }

}
