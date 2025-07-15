package com.decoracks.app.decoracks.models.repository;

import com.decoracks.app.decoracks.models.entity.StockProductoSede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StockProductoSedeRepository extends JpaRepository<StockProductoSede, Integer> {

    List<StockProductoSede> findBySedeId(int sedeId);

    StockProductoSede findByProductoIdAndSedeId(int productoId, int sedeId);

    @Query("SELECT s FROM StockProductoSede s WHERE s.sede.id = :sedeId AND s.stock > 0")
    List<StockProductoSede> findBySedeConStock(@Param("sedeId") int sedeId);
}