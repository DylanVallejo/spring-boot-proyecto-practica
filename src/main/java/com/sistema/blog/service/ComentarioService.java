package com.sistema.blog.service;

import com.sistema.blog.dto.ComentarioDto;
import com.sistema.blog.entity.Comentario;

import java.util.List;

public interface ComentarioService {
    public ComentarioDto crearComentario(long publicacionId, ComentarioDto comentarioDto);

    public List<ComentarioDto> obtenerComentariosPorPublicacionId(long publicacionId);

    public ComentarioDto obtenerComentarioPorId(Long publicacionId, Long comentarioId);
}
