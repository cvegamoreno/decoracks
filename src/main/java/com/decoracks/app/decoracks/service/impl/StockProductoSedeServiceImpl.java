package com.decoracks.app.decoracks.service.impl;

import com.decoracks.app.decoracks.models.entity.StockProductoSede;
import com.decoracks.app.decoracks.models.repository.StockProductoSedeRepository;
import com.decoracks.app.decoracks.service.StockProductoSedeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockProductoSedeServiceImpl implements StockProductoSedeService {

    private final StockProductoSedeRepository repository;

    public StockProductoSedeServiceImpl(StockProductoSedeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<StockProductoSede> findAll() {
        return repository.findAll();
    }

    @Override
    public StockProductoSede findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public StockProductoSede save(StockProductoSede stock) {
        return repository.save(stock);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    // ✅ Método extra: listar por sede
    public List<StockProductoSede> findBySedeId(int sedeId) {
        return repository.findBySedeId(sedeId);
    }

    @Override
    public StockProductoSede findByProductoAndSede(int productoId, int sedeId) {
        return repository.findByProductoIdAndSedeId(productoId, sedeId);
    }

    @Override
    public List<StockProductoSede> findBySedeConStock(int sedeId) {
        return repository.findBySedeConStock(sedeId);
    }
}
