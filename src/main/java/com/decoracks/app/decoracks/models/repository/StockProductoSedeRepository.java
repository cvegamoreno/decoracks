package com.decoracks.app.decoracks.models.repository;

import com.decoracks.app.decoracks.models.entity.StockProductoSede;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockProductoSedeRepository extends JpaRepository<StockProductoSede, Integer> {
    List<StockProductoSede> findBySedeId(int sedeId);
    StockProductoSede findByProductoIdAndSedeId(int productoId, int sedeId);
}

