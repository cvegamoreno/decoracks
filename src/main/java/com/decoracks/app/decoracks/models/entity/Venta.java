package com.decoracks.app.decoracks.models.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ventas")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "usuario_id") // vendedor
    private Usuario vendedor;

    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Usuario tecnico;

    @ManyToOne
    @JoinColumn(name = "sede_id")
    private Sede sede;

    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_actual")
    private EstadoVenta estadoActual;

    @Column(name = "total")
    private double total;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<DetalleVenta> detalles;

    public enum EstadoVenta {
        pendiente, pagado, asignado, en_reparto, entregado, cancelado
    }

    public Venta() {
    }

    public Venta(int id, Cliente cliente, Usuario vendedor, Usuario tecnico, Sede sede, LocalDateTime fecha,
            EstadoVenta estadoActual, List<DetalleVenta> detalles) {
        this.id = id;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.tecnico = tecnico;
        this.sede = sede;
        this.fecha = fecha;
        this.estadoActual = estadoActual;
        this.detalles = detalles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }

    public Usuario getTecnico() {
        return tecnico;
    }

    public void setTecnico(Usuario tecnico) {
        this.tecnico = tecnico;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public EstadoVenta getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(EstadoVenta estadoActual) {
        this.estadoActual = estadoActual;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<DetalleVenta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVenta> detalles) {
        this.detalles = detalles;
    }
}
