package com.sistema.blog.controlador;

import com.sistema.blog.dto.PublicacionDTO;
import com.sistema.blog.service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//RestController nos indica que sera un api rest
@RestController
//request  mapping indica a spring que metodos debe usar y con que endpoint
@RequestMapping("/api/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionService publicacionService;

    @GetMapping
    public List<PublicacionDTO> listarPublicaciones(){
        return publicacionService.obtenerTodasLasPublicaciones();
    }

    @PostMapping
//    @RequestBody nos indica que los valores se obtendran  del body enviado desde el cliente
    public ResponseEntity<PublicacionDTO> guardarPublicacion(@RequestBody PublicacionDTO publicacionDTO){
        return new ResponseEntity<>(publicacionService.crearPublicacion(publicacionDTO), HttpStatus.CREATED);
    }


//establecer la ruta con llaves significa que sera un parametro o variable a tomar
    @GetMapping("/{id}")
    public ResponseEntity<PublicacionDTO> obtenerPublicacionPorId(@PathVariable(name="id") long id){
        return ResponseEntity.ok(publicacionService.obtenerPublicacionPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicacionDTO> actualizarPublicacion(@RequestBody PublicacionDTO publicacionDTO, @PathVariable(name="id") long id){
        PublicacionDTO publicacionResponse = publicacionService.actualizarPublicacion(publicacionDTO,id);
        return new ResponseEntity<>(publicacionResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPublicacion(@PathVariable(name = "id") long id){
        publicacionService.eliminarPublicacion(id);
        return new ResponseEntity<>("Publicacion eliminada.", HttpStatus.OK);

    }

}
