package com.decoracks.app.decoracks.service;

import com.decoracks.app.decoracks.models.entity.Producto;
import com.decoracks.app.decoracks.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    public Producto save(Producto producto) {
        return repository.save(producto);
    }

    public List<Producto> findAll() {
        return repository.findAll();
    }

    public Optional<Producto> findById(int id) {
        return repository.findById(id);
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }
}