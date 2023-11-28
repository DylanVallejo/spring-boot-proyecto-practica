package com.sistema.blog.service;


import com.sistema.blog.dto.PublicacionDTO;
import com.sistema.blog.entity.Publicacion;
import com.sistema.blog.exeptions.ResourceNotFoundExeption;
import com.sistema.blog.repository.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicacionServiceImp implements PublicacionService{

    @Autowired
    private PublicacionRepository publicacionRepository;
    @Override
    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO){
//        convertimos el DTO  a una entidad
//        Publicacion publicacion = new Publicacion();
//        publicacion.setTitulo(publicacionDTO.getTitulo());
//        publicacion.setDescripcion(publicacionDTO.getDescripcion());
//        publicacion.setContenido(publicacionDTO.getContenido());
//
////realizamos una persistencia en la base de datos
//        Publicacion nuevaPublicacion = publicacionRepository.save(publicacion);
//
////        Convertimos de entidad a Dto  || y retornamos una respuesta como json
//        PublicacionDTO publicacionResponse = new PublicacionDTO();
//        publicacionResponse.setId(nuevaPublicacion.getId());
//        publicacionResponse.setTitulo(nuevaPublicacion.getTitulo());
//        publicacionResponse.setDescripcion(nuevaPublicacion.getDescripcion());
//        publicacionResponse.setContenido(nuevaPublicacion.getContenido());
//
//        return publicacionResponse;

        Publicacion publicacion = mapearEntidad(publicacionDTO);
        Publicacion nuevaPublicacion = publicacionRepository.save(publicacion);
        PublicacionDTO publicacionResponse = mapearDTO(nuevaPublicacion);
        return publicacionResponse;
    }

    @Override
    public List<PublicacionDTO> obtenerTodasLasPublicaciones(){
        List<Publicacion> publicaciones = publicacionRepository.findAll();
//        trae una lista de publicaciones las mapea y las retorna como una lista
        return publicaciones.stream().map(publicacion -> mapearDTO(publicacion)).collect(Collectors.toList());
    }

    @Override
    public PublicacionDTO obtenerPublicacionPorId(long id) {
        Publicacion publicacion = publicacionRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundExeption("Publicacion", "id", id));
        return mapearDTO(publicacion);
    }

    @Override
    public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, long id) {
        Publicacion publicacion = publicacionRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundExeption("Publicacion", "id", id));
        publicacion.setTitulo(publicacionDTO.getTitulo());
        publicacion.setDescripcion(publicacionDTO.getDescripcion());
        publicacion.setContenido(publicacionDTO.getContenido());


        Publicacion publicacionActualizada = publicacionRepository.save(publicacion);
        return mapearDTO(publicacionActualizada);

    }


    //    CONVIERTE la entidad a un DTO
    private PublicacionDTO mapearDTO(Publicacion publicacion){

        PublicacionDTO publicacionDTO = new PublicacionDTO();

        publicacionDTO.setId(publicacion.getId());
        publicacionDTO.setTitulo(publicacion.getTitulo());
        publicacionDTO.setDescripcion(publicacion.getDescripcion());
        publicacionDTO.setContenido(publicacion.getContenido());

        return publicacionDTO;
    }
//convierto de un DTO A una ENTIDAD
    private Publicacion mapearEntidad(PublicacionDTO publicacionDTO){

        Publicacion publicacion = new Publicacion();

        publicacion.setId(publicacionDTO.getId());
        publicacion.setTitulo(publicacionDTO.getTitulo());
        publicacion.setDescripcion(publicacionDTO.getDescripcion());
        publicacion.setContenido(publicacionDTO.getContenido());

        return publicacion;
    }

}
