package com.sistema.blog.entity;

import jakarta.persistence.*;
//con este entidad creamos directamente al ejecutar el programa la tabla publicaciones con sus respectivos campos
@Entity
@Table(name = "publicaciones",uniqueConstraints = {@UniqueConstraint(columnNames = "titulo")})
public class Publicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="titulo", nullable = false)
    private String titulo;
    @Column(name="descripcion", nullable = false)
    private String descripcion;
    @Column(name="contenido", nullable = false)
    private String contenido;

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

    public Publicacion() {
    }

    public Publicacion(Long id, String titulo, String descripcion, String contenido) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.contenido = contenido;
    }
}
