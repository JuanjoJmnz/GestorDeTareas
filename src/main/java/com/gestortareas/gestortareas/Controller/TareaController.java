package com.gestortareas.gestortareas.Controller;

import com.gestortareas.gestortareas.DTO.TareaPatchDTO;
import com.gestortareas.gestortareas.Entity.Tarea;
import com.gestortareas.gestortareas.Repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    @Autowired
    private TareaRepository tareaRepo;

    @GetMapping
    public List<Tarea> listar() {
        return tareaRepo.findAll();
    }

    @PostMapping
    public Tarea crear(@RequestBody Tarea tarea) {
        return tareaRepo.save(tarea);
    }

    @PutMapping("/{id}/completar")
    public ResponseEntity<Tarea> marcarComoCompletada(@PathVariable Long id) {
        Optional<Tarea> tarea = tareaRepo.findById(id);
        if (tarea.isPresent()) {
            Tarea t = tarea.get();
            t.setCompletada(true);
            return ResponseEntity.ok(tareaRepo.save(t));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarea> actualizarTarea(@PathVariable Long id, @RequestBody Tarea tareaActualizada) {
        Optional<Tarea> tareaExistente = tareaRepo.findById(id);
        if (tareaExistente.isPresent()) {
            Tarea tarea = tareaExistente.get();
            tarea.setTitulo(tareaActualizada.getTitulo());
            tarea.setDescripcion(tareaActualizada.getDescripcion());
            tarea.setCompletada(tareaActualizada.isCompletada());
            return ResponseEntity.ok(tareaRepo.save(tarea));
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Tarea> actualizarParcialmente(@PathVariable Long id, @RequestBody TareaPatchDTO datos) {
        Optional<Tarea> tareaOpt = tareaRepo.findById(id);
        if (tareaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Tarea tarea = tareaOpt.get();

        if (datos.getTitulo() != null) {
            tarea.setTitulo(datos.getTitulo());
        }

        if (datos.getDescripcion() != null) {
            tarea.setDescripcion(datos.getDescripcion());
        }

        if (datos.getCompletada() != null) {
            tarea.setCompletada(datos.getCompletada());
        }

        return ResponseEntity.ok(tareaRepo.save(tarea));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        tareaRepo.deleteById(id);
    }

    @GetMapping("/pendientes")
    public List<Tarea> tareasPendientes() {
        return tareaRepo.findByCompletada(false);
    }

    @GetMapping("/completadas")
    public List<Tarea> tareasCompletadas() {
        return tareaRepo.findByCompletada(true);
    }
}
