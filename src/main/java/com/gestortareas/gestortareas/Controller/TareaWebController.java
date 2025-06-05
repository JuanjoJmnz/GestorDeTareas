package com.gestortareas.gestortareas.Controller;

import com.gestortareas.gestortareas.Entity.Tarea;
import com.gestortareas.gestortareas.Repository.TareaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;  // Correcto
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class TareaWebController {

    private final TareaRepository tareaRepo;

    @GetMapping("/")
    public String listarTareas(Model model) {
        try {
            List<Tarea> tareas = tareaRepo.findAllByOrderByFechaCreacionDesc();
            model.addAttribute("tareas", tareas);
            model.addAttribute("tarea", new Tarea());
            return "index";
        } catch (Exception e) {
            System.err.println("Error al cargar tareas: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("error", "Error al cargar las tareas");
            return "error";
        }
    }

    @PostMapping("/tareas")
    public String crearTarea(@ModelAttribute Tarea tarea) {
        try {
            tareaRepo.save(tarea);
            return "redirect:/";
        } catch (Exception e) {
            System.err.println("Error al guardar tarea: " + e.getMessage());
            e.printStackTrace();
            return "redirect:/?error=true";
        }
    }

    @PostMapping("/tareas/{id}/eliminar")
    public String eliminarTarea(@PathVariable Long id) {
        try {
            tareaRepo.deleteById(id);
            return "redirect:/";
        } catch (Exception e) {
            System.err.println("Error al eliminar tarea: " + e.getMessage());
            e.printStackTrace();
            return "redirect:/?error=true";
        }
    }
}