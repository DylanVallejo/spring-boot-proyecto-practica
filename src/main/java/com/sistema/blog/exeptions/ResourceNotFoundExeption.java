package com.sistema.blog.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


//ResourceNotFoundExeption es una sublcase que extiende de RuntimeException por lo que debo usar
// super para llamar al constructor de la clase padre en esta caso RuntimeException

//en esta clase manejaremos los mensajes de exceptions para retornar un status code de not found
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundExeption extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private String nombreDelRecurso;
    private String nombreDelCampo;
    private long valorDelCampo;

    public ResourceNotFoundExeption(String nombreDelRecurso, String nombreDelCampo, long valorDelCampo) {
//        '%s' usar esto significa que recibira parametros
        super(String.format(" %s publicacion no encontrada con : %s : '%s' ",nombreDelRecurso,nombreDelCampo,valorDelCampo));
        this.nombreDelRecurso = nombreDelRecurso;
        this.nombreDelCampo = nombreDelCampo;
        this.valorDelCampo = valorDelCampo;
    }


    public String getNombreDelRecurso() {
        return nombreDelRecurso;
    }

    public void setNombreDelRecurso(String nombreDelRecurso) {
        this.nombreDelRecurso = nombreDelRecurso;
    }

    public String getNombreDelCampo() {
        return nombreDelCampo;
    }

    public void setNombreDelCampo(String nombreDelCampo) {
        this.nombreDelCampo = nombreDelCampo;
    }

    public long getValorDelCampo() {
        return valorDelCampo;
    }

    public void setValorDelCampo(long valorDelCampo) {
        this.valorDelCampo = valorDelCampo;
    }
}
