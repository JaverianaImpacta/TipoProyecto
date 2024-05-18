package edu.javeriana.ingenieria.social.tipo_proyecto.controlador;

import edu.javeriana.ingenieria.social.tipo_proyecto.dominio.Tipo;
import edu.javeriana.ingenieria.social.tipo_proyecto.servicio.ServicioTipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/tipoProyectos")
@CrossOrigin(origins="http://localhost:4200")
public class ControladorTipo {
    @Autowired
    private ServicioTipo servicio;

    @GetMapping("listar")
    public List<Tipo> obtenerTipos(){
        return servicio.obtenerTipos();
    }

    @GetMapping("obtener")
    public ResponseEntity<Tipo> obtenerTipo(@RequestParam Integer id){
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.obtenerTipo(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(servicio.obtenerTipo(id), HttpStatus.OK);
    }

    @GetMapping("obtenerDescripcion")
    public ResponseEntity<Tipo> obtenerTipo(@RequestParam String descripcion){
        if(descripcion == null || descripcion.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(!servicio.tipoExiste(descripcion)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(servicio.obtenerTipo(descripcion), HttpStatus.OK);
    }

    @PostMapping("crear")
    public ResponseEntity<Tipo> crearTipo(@RequestBody Tipo tipo){
        if(tipo == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.tipoExiste(tipo.getDescripcion()) || servicio.tipoExiste(tipo.getId())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(servicio.crearTipo(tipo), HttpStatus.CREATED);
    }

    @PutMapping("actualizar")
    public ResponseEntity<Tipo> actualizarTipo(@RequestParam Integer id, @RequestBody Tipo tipo){
        if(id == null || tipo == null || !Objects.equals(tipo.getId(), id)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.actualizarTipo(id, tipo) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tipo, HttpStatus.OK);
    }

    @DeleteMapping("eliminar")
    public ResponseEntity<HttpStatus> eliminarTipo(@RequestParam Integer id){
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.borrarTipo(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
