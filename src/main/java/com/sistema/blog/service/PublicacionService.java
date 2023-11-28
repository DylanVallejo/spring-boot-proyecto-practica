package com.sistema.blog.service;

import com.sistema.blog.dto.PublicacionDTO;

import java.util.List;


public interface PublicacionService {

    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDto);

    public List<PublicacionDTO> obtenerTodasLasPublicaciones();

    public PublicacionDTO obtenerPublicacionPorId(long id);

    public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, long id);
}
