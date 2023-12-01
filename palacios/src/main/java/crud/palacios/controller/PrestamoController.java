package crud.palacios.controller;
import crud.palacios.service.UsuarioService;
import org.springframework.validation.annotation.Validated;
import crud.palacios.persistence.entities.Prestamo;
import crud.palacios.service.PrestamoService;
import crud.palacios.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
@Controller
@RequestMapping("/prestamos")
public class PrestamoController {
    @Autowired
    private PrestamoService prestamoService;
    @Autowired
    private PeliculaService peliculaService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String getAllPrestamos(Model model) {
        model.addAttribute("prestamos", prestamoService.getAllPrestamos());
        return "prestamo/listaPrestamos";
    }

    @GetMapping("/crear")
    public String mostrarFormularioPrestamo(Model model) {
        model.addAttribute("prestamo", new Prestamo());
        model.addAttribute("peliculas", peliculaService.getAllPeliculas());
        model.addAttribute("usuarios",usuarioService.getAllUsuarios());
        return "prestamo/formularioPrestamo";
    }

    @PostMapping("/crear")
    public String crearPrestamo(@Validated @ModelAttribute Prestamo prestamo, BindingResult result) {
        if (result.hasErrors()) {
            return "prestamo/formularioPrestamo";
        }
        // Validar no nulidad de fechas
        if (prestamo.getFechaPrestamo() == null || prestamo.getFechaDevolucion() == null) {
            result.rejectValue("fechaPrestamo", "NotNull.prestamo.fechaPrestamo", "La fecha de préstamo no puede ser nula");
            result.rejectValue("fechaDevolucion", "NotNull.prestamo.fechaDevolucion", "La fecha de devolución no puede ser nula");
            return "prestamo/formularioPrestamo";
        }

        prestamoService.savePrestamo(prestamo);
        return "redirect:/prestamos";
    }

   // @GetMapping("/editar/{id}")
    //public String mostrarFormularioEditarPrestamo(@PathVariable Long id, Model model) {
     //   Prestamo prestamo = prestamoService.getPrestamoById(id).orElse(null);
      //  model.addAttribute("prestamo", prestamo);
       // return "prestamo/formularioPrestamo";
    //}

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarPrestamo(@PathVariable Long id, Model model) {
        Prestamo prestamo = prestamoService.getPrestamoById(id).orElse(null);

        if (prestamo != null) {
            model.addAttribute("prestamo", prestamo);
            model.addAttribute("peliculas", peliculaService.getAllPeliculas());
            model.addAttribute("usuarios", usuarioService.getAllUsuarios());


            model.addAttribute("selectedPelicula", prestamo.getPelicula().getIdPelicula());
            model.addAttribute("selectedUsuario", prestamo.getUsuario().getIdUsuario());
        }

        return "prestamo/formularioPrestamo";
    }

    @PostMapping("/editar/{id}")
    public String editarPrestamo(@PathVariable Long id,@Validated @ModelAttribute Prestamo prestamo, BindingResult result) {
        if (result.hasErrors()) {
            return "prestamo/formularioPrestamo";
        }
        prestamo.setIdPrestamo(id);
        prestamoService.savePrestamo(prestamo);
        return "redirect:/prestamos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPrestamo(@PathVariable Long id) {
        prestamoService.deletePrestamo(id);
        return "redirect:/prestamos";
    }
}