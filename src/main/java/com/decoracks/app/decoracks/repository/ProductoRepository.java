package com.decoracks.app.decoracks.repository;

import com.decoracks.app.decoracks.models.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}