package com.decoracks.app.decoracks.service.impl;

import com.decoracks.app.decoracks.models.entity.Producto;
import com.decoracks.app.decoracks.models.repository.ProductoRepository;
import com.decoracks.app.decoracks.service.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository repository;

    public ProductoServiceImpl(ProductoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Producto save(Producto producto) {
        return repository.save(producto);
    }

    @Override
    public List<Producto> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Producto> get(int id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Override
    public Producto update(int id, Producto producto) {
        return repository.findById(id)
                .map(p -> {
                    p.setNombre(producto.getNombre());
                    p.setDescripcion(producto.getDescripcion());
                    p.setPrecio(producto.getPrecio());
                    p.setCategoria(producto.getCategoria());
                    // stockSedes y detalles no se actualizan aquí directamente
                    return repository.save(p);
                })
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
    }

    @Override
    public Producto findById(int id) {
        return repository.findById(id).orElse(null); // O lanza excepción si prefieres
    }
}