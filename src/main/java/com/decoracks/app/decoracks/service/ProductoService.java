package com.decoracks.app.decoracks.service;

import com.decoracks.app.decoracks.models.entity.Producto;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductoService {
    Producto save(Producto producto);
    List<Producto> findAll();
    Optional<Producto> get(int id);
    void deleteById(int id);
    Producto update(int id, Producto producto);
    Producto findById(int id);
}