package crud.palacios.controller;
import org.springframework.validation.annotation.Validated;
import crud.palacios.persistence.entities.Usuario;
import crud.palacios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String getAllUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.getAllUsuarios());
        return "usuario/listaUsuarios";
    }

    @GetMapping("/crear")
    public String mostrarFormularioUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuario/formularioUsuario";
    }

    @PostMapping("/crear")
    public String crearUsuario(@Validated @ModelAttribute Usuario usuario, BindingResult result) {
        if (result.hasErrors()) {
            return "usuario/formularioUsuario";
        }
        usuarioService.saveUsuario(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarUsuario(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioService.getUsuarioById(id).orElse(null);
        model.addAttribute("usuario", usuario);
        return "usuario/formularioUsuario";
    }

    @PostMapping("/editar/{id}")
    public String editarUsuario(@PathVariable Long id,@Validated @ModelAttribute Usuario usuario, BindingResult result) {
        if (result.hasErrors()) {
            return "usuario/formularioUsuario";
        }
        usuario.setIdUsuario(id);
        usuarioService.saveUsuario(usuario);
        return "redirect:/usuarios";
    }

  //  @GetMapping("/eliminar/{id}")
   // public String eliminarUsuario(@PathVariable Long id) {
    //    usuarioService.deleteUsuario(id);
     //   return "redirect:/usuarios";
    //}

    @GetMapping("/eliminar/{id}")
    public String mostrarFormularioEliminarUsuario(@PathVariable Long id, Model model) {
        if (usuarioService.isUsuarioRegistradoEnPrestamo(id)) {
            model.addAttribute("advertencia", "No se puede eliminar el usuario, está registrado en un préstamo.");
        } else {
            usuarioService.deleteUsuario(id);
        }
            model.addAttribute("usuarios", usuarioService.getAllUsuarios());
            return "/usuario/listaUsuarios";
        }
}