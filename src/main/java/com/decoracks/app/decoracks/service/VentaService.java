package com.decoracks.app.decoracks.service;

import java.util.List;
import java.util.Optional;

import com.decoracks.app.decoracks.models.entity.Venta;

public interface VentaService {
    Venta save(Venta venta);
    List<Venta> findAll();
    Optional<Venta> findById(int id);
}
