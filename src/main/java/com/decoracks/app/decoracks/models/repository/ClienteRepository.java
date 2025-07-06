package com.decoracks.app.decoracks.models.repository;

import com.decoracks.app.decoracks.models.entity.Cliente;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByDni(String dni);
}