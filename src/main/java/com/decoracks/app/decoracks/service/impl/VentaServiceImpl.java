package com.decoracks.app.decoracks.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.decoracks.app.decoracks.models.entity.Venta;
import com.decoracks.app.decoracks.models.repository.VentaRepository;
import com.decoracks.app.decoracks.service.VentaService;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Override
    public Venta save(Venta venta) {
        return ventaRepository.save(venta);
    }

    @Override
    public List<Venta> findAll() {
        return ventaRepository.findAll();
    }

    @Override
    public Optional<Venta> findById(int id) {
        return ventaRepository.findById(id);
    }
}