package com.sistema.blog.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

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

//    debemos definir la relacion de comentarios con publciaciones

//    utilizamos mappedBy para indicar que mi mapeo ya se realiza en la entidad secundaria y no necesito
//    hacerlo en la principal
//   CascadeType.ALL y orphanRemoval nos permite eliminar datos que se relacionan es decir una publicacion eliminada eliminara todos los comentario
    @OneToMany(mappedBy = "publicacion",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comentario> comentarios = new HashSet<>();

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

    public Publicacion() {
    }

    public Publicacion(Long id, String titulo, String descripcion, String contenido) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.contenido = contenido;
    }
}
