package edu.javeriana.ingenieria.social.tipo_proyecto.servicio;

import edu.javeriana.ingenieria.social.tipo_proyecto.dominio.Tipo;
import edu.javeriana.ingenieria.social.tipo_proyecto.repositorio.RepositorioTipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioTipo {
    @Autowired
    private RepositorioTipo repositorio;

    public List<Tipo> obtenerTipos(){
        return repositorio.findAll();
    }

    public Tipo obtenerTipo(Integer id){
        return repositorio.findById(id).orElse(null);
    }

    public Tipo obtenerTipo(String descripcion){
        return repositorio.findOneByDescripcion(descripcion);
    }

    public Tipo crearTipo(Tipo tipo){
        return repositorio.save(tipo);
    }

    public Tipo actualizarTipo(Integer id, Tipo tipo){
        if(repositorio.findById(id).orElse(null) == null){
            return null;
        }
        tipo.setId(id);
        return repositorio.save(tipo);
    }

    public Tipo borrarTipo(Integer id){
        Tipo aux = repositorio.findById(id).orElse(null);
        if(aux == null){
            return aux;
        }
        repositorio.delete(aux);
        return aux;
    }

    public boolean tipoExiste(Integer id){
        return repositorio.existsById(id);
    }

    public boolean tipoExiste(String descripcion){
        return repositorio.existsByDescripcion(descripcion);
    }
}

