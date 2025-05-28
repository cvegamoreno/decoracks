package com.decoracks.app.decoracks.models.repository;

import com.decoracks.app.decoracks.models.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}