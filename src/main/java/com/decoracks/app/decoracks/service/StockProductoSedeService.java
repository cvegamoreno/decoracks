package com.decoracks.app.decoracks.service;

import com.decoracks.app.decoracks.models.entity.StockProductoSede;

import java.util.List;

public interface StockProductoSedeService {
    List<StockProductoSede> findAll();
    StockProductoSede findById(int id);
    StockProductoSede save(StockProductoSede stock);
    void deleteById(int id);
    List<StockProductoSede> findBySedeId(int sedeId);
    StockProductoSede findByProductoAndSede(int productoId, int sedeId);
}