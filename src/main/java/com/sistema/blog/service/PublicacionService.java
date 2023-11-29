package com.sistema.blog.service;

import com.sistema.blog.dto.PublicacionDTO;
import com.sistema.blog.dto.PublicacionRespuesta;

import java.util.List;


public interface PublicacionService {

    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDto);

    public PublicacionRespuesta obtenerTodasLasPublicaciones(int numeroDePagina, int medidaDePagina, String sortBy,String sortDir);

    public PublicacionDTO obtenerPublicacionPorId(long id);

    public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, long id);

    public void eliminarPublicacion(long id);
}
