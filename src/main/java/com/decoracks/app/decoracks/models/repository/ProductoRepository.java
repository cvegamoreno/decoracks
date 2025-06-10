package com.decoracks.app.decoracks.models.repository;

import com.decoracks.app.decoracks.models.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}