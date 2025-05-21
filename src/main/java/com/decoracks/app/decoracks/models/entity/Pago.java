package com.decoracks.app.decoracks.models.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "venta_id")
    private Venta venta;

    @Column(name = "codigo_operacion")
    private String codigoOperacion;

    @Column(name = "fecha_pago")
    private LocalDateTime fechaPago;

    public Pago() {
    }

    public Pago(int id, Venta venta, String codigoOperacion, LocalDateTime fechaPago) {
        this.id = id;
        this.venta = venta;
        this.codigoOperacion = codigoOperacion;
        this.fechaPago = fechaPago;
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

    public String getCodigoOperacion() {
        return codigoOperacion;
    }

    public void setCodigoOperacion(String codigoOperacion) {
        this.codigoOperacion = codigoOperacion;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }
}
