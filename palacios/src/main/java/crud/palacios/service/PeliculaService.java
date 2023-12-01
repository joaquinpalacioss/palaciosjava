package crud.palacios.service;

import crud.palacios.persistence.entities.Pelicula;
import crud.palacios.persistence.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import crud.palacios.persistence.repository.PrestamoRepository;

@Service
public class PeliculaService {
    @Autowired
    private PeliculaRepository peliculaRepository;
    @Autowired
    private PrestamoRepository prestamoRepository;

    public List<Pelicula> getAllPeliculas() {
        return peliculaRepository.findAll();
    }

    public Optional<Pelicula> getPeliculaById(Long id) {
        return peliculaRepository.findById(id);
    }

    public Pelicula savePelicula(Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    public void deletePelicula(Long id) {
        peliculaRepository.deleteById(id);
    }

    public boolean isPeliculaRegistradaEnPrestamo(Long idPelicula) {
        return prestamoRepository.existsById(idPelicula);
    }

}
