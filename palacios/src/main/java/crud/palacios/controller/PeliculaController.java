package crud.palacios.controller;
import org.springframework.validation.annotation.Validated;
import crud.palacios.persistence.entities.Pelicula;
import crud.palacios.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
@Controller
@RequestMapping("/peliculas")
public class PeliculaController {
    @Autowired
    private PeliculaService peliculaService;

    @GetMapping
    public String getAllPeliculas(Model model) {
        model.addAttribute("peliculas", peliculaService.getAllPeliculas());
        return "pelicula/listaPeliculas";
    }

    @GetMapping("/crear")
    public String mostrarFormularioPelicula(Model model) {
        model.addAttribute("pelicula", new Pelicula());
        return "pelicula/formularioPelicula";
    }

    @PostMapping("/crear")
    public String crearPelicula(@Validated @ModelAttribute Pelicula pelicula, BindingResult result) {
        if (result.hasErrors()) {
            return "pelicula/formularioPelicula";
        }
        peliculaService.savePelicula(pelicula);
        return "redirect:/peliculas";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarPelicula(@PathVariable Long id, Model model) {
        Pelicula pelicula = peliculaService.getPeliculaById(id).orElse(null);
        model.addAttribute("pelicula", pelicula);
        return "pelicula/formularioPelicula";
    }

    @PostMapping("/editar/{id}")
    public String editarPelicula(@PathVariable Long id,@Validated @ModelAttribute Pelicula pelicula, BindingResult result) {
        if (result.hasErrors()) {
            return "pelicula/formularioPelicula";
        }

        pelicula.setIdPelicula(id);
        peliculaService.savePelicula(pelicula);
        return "redirect:/peliculas";
    }

   // @GetMapping("/eliminar/{id}")
    //public String eliminarPelicula(@PathVariable Long id) {
     //   peliculaService.deletePelicula(id);
      //  return "redirect:/peliculas";
    //}

    @GetMapping("/eliminar/{id}")
    public String mostrarFormularioEliminarPelicula(@PathVariable Long id, Model model) {
        if (peliculaService.isPeliculaRegistradaEnPrestamo(id)) {
            model.addAttribute("advertencia", "No se puede eliminar la película, está registrada en un préstamo.");
            model.addAttribute("peliculas", peliculaService.getAllPeliculas());
            return "pelicula/listaPeliculas";
        }

        peliculaService.deletePelicula(id);
        return "redirect:/peliculas";
    }
}
