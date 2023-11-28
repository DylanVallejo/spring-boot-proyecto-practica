package com.sistema.blog.dto;

public class PublicacionDTO {

//    DTO(DATA TRANSFRE OBJECT) sirven para  trasnferir datos reduciendo la
//    cantidad de inforamcion transferida evitando exponer la estructura interna de los objetos
//    no se debe confundir con un DAO(DATA ACCES OBJECT) que sera el encargado de realizar las operaciones CRUD

//    EN RESUMEN EL DTO MANDA LA INFO NECESARIA AL DAO PARA SU RESPECTIVA MANIPULACION EN LA BASE


    private Long id;
    private String titulo;
    private String descripcion;
    private String contenido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }


    public PublicacionDTO() {
    }

    public PublicacionDTO(Long id, String titulo, String descripcion, String contenido) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.contenido = contenido;
    }
}
