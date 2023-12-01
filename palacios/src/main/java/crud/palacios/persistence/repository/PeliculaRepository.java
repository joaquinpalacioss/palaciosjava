package crud.palacios.persistence.repository;

import crud.palacios.persistence.entities.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
    // Puedes agregar consultas personalizadas si es necesario
}