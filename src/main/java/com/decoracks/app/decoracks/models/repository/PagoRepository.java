package com.decoracks.app.decoracks.models.repository;

import com.decoracks.app.decoracks.models.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoRepository extends JpaRepository<Pago, Integer> {
}