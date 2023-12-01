package crud.palacios.persistence.repository;

import crud.palacios.persistence.entities.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    // Puedes agregar consultas personalizadas si es necesario
}
