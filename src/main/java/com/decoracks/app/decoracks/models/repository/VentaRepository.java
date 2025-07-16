package com.decoracks.app.decoracks.models.repository;

import com.decoracks.app.decoracks.models.entity.Venta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta, Integer> {
    List<Venta> findByTecnicoIsNull();
}