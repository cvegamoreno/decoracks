package com.decoracks.app.decoracks.models.repository;

import com.decoracks.app.decoracks.models.entity.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Integer> {
}