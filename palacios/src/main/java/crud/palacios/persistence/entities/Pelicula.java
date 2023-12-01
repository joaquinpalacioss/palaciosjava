package crud.palacios.persistence.entities;
import jakarta.persistence.*;
import java.util.List;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
@Entity
@Table(name = "peliculas")
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPelicula;

    @NotBlank
    private String titulo;
    @NotBlank
    private String director;
    @NotNull
    private int fechaEstreno;
    @NotBlank
    private String genero;

    public Long getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Long idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(int fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    // Relación con la tabla de préstamos
    @OneToMany(mappedBy = "pelicula")
    private List<Prestamo> prestamos;
}