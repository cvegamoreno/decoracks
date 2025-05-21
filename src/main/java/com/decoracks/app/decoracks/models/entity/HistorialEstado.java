package com.decoracks.app.decoracks.models.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.decoracks.app.decoracks.models.entity.Venta.EstadoVenta;

@Entity
@Table(name = "historial_estados")
public class HistorialEstado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Venta venta;

    @Enumerated(EnumType.STRING)
    private Venta.EstadoVenta estado;

    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "foto_entrega")
    private String fotoEntrega; // URL o nombre de archivo

    public HistorialEstado() {
    }

    public HistorialEstado(int id, Venta venta, EstadoVenta estado, LocalDateTime fecha, Usuario usuario,
            String fotoEntrega) {
        this.id = id;
        this.venta = venta;
        this.estado = estado;
        this.fecha = fecha;
        this.usuario = usuario;
        this.fotoEntrega = fotoEntrega;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Venta.EstadoVenta getEstado() {
        return estado;
    }

    public void setEstado(Venta.EstadoVenta estado) {
        this.estado = estado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getFotoEntrega() {
        return fotoEntrega;
    }

    public void setFotoEntrega(String fotoEntrega) {
        this.fotoEntrega = fotoEntrega;
    }
}
