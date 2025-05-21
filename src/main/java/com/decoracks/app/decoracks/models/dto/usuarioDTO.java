package com.decoracks.app.decoracks.models.dto;

public class usuarioDTO {
    private String nombre, email, password, rol;
    private int estado, sede_id, id;

    public usuarioDTO() {
    }

    public usuarioDTO(String nombre, String email, String password, String rol, int estado, int sede_id, int id) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.estado = estado;
        this.sede_id = sede_id;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
    public int getEstado() {
        return estado;
    }
    public void setEstado(int estado) {
        this.estado = estado;
    }
    public int getSede_id() {
        return sede_id;
    }
    public void setSede_id(int sede_id) {
        this.sede_id = sede_id;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
