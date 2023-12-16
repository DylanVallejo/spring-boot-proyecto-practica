package com.sistema.blog.service;

import com.sistema.blog.dto.ComentarioDto;
import com.sistema.blog.entity.Comentario;
import com.sistema.blog.entity.Publicacion;
import com.sistema.blog.exeptions.BlogAppException;
import com.sistema.blog.exeptions.ResourceNotFoundExeption;
import com.sistema.blog.repository.ComentarioRepository;
import com.sistema.blog.repository.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComentarioServicioImp implements ComentarioService{

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private PublicacionRepository publicacionRepository;
    @Override
    public ComentarioDto crearComentario(long publicacionId, ComentarioDto comentarioDto) {
        Comentario comentario = mapearEntidad(comentarioDto);
        Publicacion publicacion = publicacionRepository.findById(publicacionId).orElseThrow(()->new ResourceNotFoundExeption("Publicacion", "id", publicacionId));
        comentario.setPublicacion(publicacion);
        Comentario nuevoComentario = comentarioRepository.save(comentario);
        return mapearDTO(nuevoComentario);
    }

    @Override
    public List<ComentarioDto> obtenerComentariosPorPublicacionId(long publicacionId) {
        List<Comentario> comentarios = comentarioRepository.findByPublicacionId(publicacionId);
        return comentarios.stream().map(comentario -> mapearDTO(comentario)).collect(Collectors.toList());
    }

    @Override
    public ComentarioDto obtenerComentarioPorId(Long publicacionId ,Long comentarioId) {
        Publicacion publicacion = publicacionRepository.findById(publicacionId).orElseThrow(()->new ResourceNotFoundExeption("Publicacion", "id", publicacionId));
        Comentario comentario = comentarioRepository.findById(comentarioId).orElseThrow(() -> new ResourceNotFoundExeption("Comentario", "id", comentarioId) );
        if(!comentario.getPublicacion().getId().equals(publicacion.getId())){
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "el comentario no pertenece a la publicacion" );

        }
        return mapearDTO(comentario);
    }

    //    convertir de dto a entidad
    private ComentarioDto mapearDTO(Comentario comentario){
        ComentarioDto comentarioDto = new ComentarioDto();
        comentarioDto.setId(comentario.getId());
        comentarioDto.setNombre(comentario.getNombre());
        comentarioDto.setEmail(comentario.getEmail());
        comentarioDto.setCuerpo(comentario.getCuerpo());
        return comentarioDto;
    }

//mapeo de dto a entidad
    private Comentario mapearEntidad(ComentarioDto comentarioDto){
        Comentario comentario = new Comentario();
        comentario.setId(comentarioDto.getId());
        comentario.setNombre(comentarioDto.getNombre());
        comentario.setEmail(comentarioDto.getEmail());
        comentario.setCuerpo(comentarioDto.getCuerpo());
        return comentario;
    }
}
