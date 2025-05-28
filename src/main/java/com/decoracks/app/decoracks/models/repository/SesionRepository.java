package com.decoracks.app.decoracks.models.repository;

import com.decoracks.app.decoracks.models.entity.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SesionRepository extends JpaRepository<Sesion, Integer> {
}