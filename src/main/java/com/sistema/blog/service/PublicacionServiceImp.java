package com.sistema.blog.service;


import com.sistema.blog.dto.PublicacionDTO;
import com.sistema.blog.dto.PublicacionRespuesta;
import com.sistema.blog.entity.Publicacion;
import com.sistema.blog.exeptions.ResourceNotFoundExeption;
import com.sistema.blog.repository.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public PublicacionRespuesta obtenerTodasLasPublicaciones(int numeroDePagina, int medidaDePagina, String sortBy,String sortDir){

//            ordenando de forma ascendente o descendente
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
//        listo mediante pageable con un numero de cantidad y elementos
        Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina, sort);
        Page<Publicacion> publicaciones = publicacionRepository.findAll(pageable);
//        obtengo los elementos despeus de utilizar pageable
        List<Publicacion> listaDePublicaciones = publicaciones.getContent();
//        trae una lista de publicaciones las mapea y las retorna como una lista
        List<PublicacionDTO> contenido = listaDePublicaciones.stream().map(publicacion -> mapearDTO(publicacion)).collect(Collectors.toList());

        PublicacionRespuesta publicacionRespuesta = new PublicacionRespuesta();
        publicacionRespuesta.setContenido(contenido);
        publicacionRespuesta.setNumeroDePagina(publicaciones.getNumber());
        publicacionRespuesta.setMedidaDePagina(publicaciones.getSize());
        publicacionRespuesta.setTotalElementos(publicaciones.getTotalElements());
        publicacionRespuesta.setTotalPaginas(publicaciones.getTotalPages());
        publicacionRespuesta.setUltima(publicaciones.isLast());
        return publicacionRespuesta;
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

//  al eliminar datos retorna un tipo void y no reuqiero de un return statement
    @Override
    public void eliminarPublicacion(long id){
        Publicacion publicacion = publicacionRepository.findById(id).orElseThrow(()->new ResourceNotFoundExeption("Publicacion", "id", id));
//        primero busco por id y luego elimino la entidad
        publicacionRepository.delete(publicacion);
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
