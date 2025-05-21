package com.decoracks.app.decoracks.repository;

import com.decoracks.app.decoracks.models.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}