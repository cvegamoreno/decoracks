package com.decoracks.app.decoracks.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.decoracks.app.decoracks.models.entity.DetalleVenta;
import com.decoracks.app.decoracks.models.entity.Venta;
import com.decoracks.app.decoracks.models.repository.DetalleVentaRepository;
import com.decoracks.app.decoracks.service.DetalleVentaService;

@Service
public class DetalleVentaServiceImpl implements DetalleVentaService {

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Override
    public DetalleVenta save(DetalleVenta detalle) {
        return detalleVentaRepository.save(detalle);
    }

    @Override
    public List<DetalleVenta> findByVenta(Venta venta) {
        return detalleVentaRepository.findByVenta(venta);
    }
}

