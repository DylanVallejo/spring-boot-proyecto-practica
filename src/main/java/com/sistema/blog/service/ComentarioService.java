package com.sistema.blog.service;

import com.sistema.blog.dto.ComentarioDto;

public interface ComentarioService {
    public ComentarioDto crearComentario(long publicacionId, ComentarioDto comentarioDto);
}
