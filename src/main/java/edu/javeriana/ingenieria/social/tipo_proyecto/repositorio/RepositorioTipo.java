package edu.javeriana.ingenieria.social.tipo_proyecto.repositorio;

import edu.javeriana.ingenieria.social.tipo_proyecto.dominio.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioTipo extends JpaRepository<Tipo, Integer> {
    boolean existsByDescripcion(String descripcion);

    Tipo findOneByDescripcion(String descripcion);
}
