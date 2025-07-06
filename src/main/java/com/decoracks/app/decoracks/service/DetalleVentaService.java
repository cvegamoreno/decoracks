package com.decoracks.app.decoracks.service;

import java.util.List;

import com.decoracks.app.decoracks.models.entity.DetalleVenta;
import com.decoracks.app.decoracks.models.entity.Venta;

public interface DetalleVentaService {
    DetalleVenta save(DetalleVenta detalle);
    List<DetalleVenta> findByVenta(Venta venta);
}