package com.decoracks.app.decoracks.service;

import com.decoracks.app.decoracks.models.entity.Categoria;
import com.decoracks.app.decoracks.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public Categoria save(Categoria categoria) {
        return repository.save(categoria);
    }

    public List<Categoria> findAll() {
        return repository.findAll();
    }

    public Optional<Categoria> findById(int id) {
        return repository.findById(id);
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }
}