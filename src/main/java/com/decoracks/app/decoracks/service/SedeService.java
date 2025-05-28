package com.decoracks.app.decoracks.service;

import com.decoracks.app.decoracks.models.entity.Sede;
import com.decoracks.app.decoracks.models.repository.SedeRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SedeService {

    private final SedeRepository repository;

    public SedeService(SedeRepository repository) {
        this.repository = repository;
    }

    public Sede save(Sede sede) {
        return repository.save(sede);
    }

    public List<Sede> findAll() {
        return repository.findAll();
    }

    public Optional<Sede> findById(int id) {
        return repository.findById(id);
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }
}