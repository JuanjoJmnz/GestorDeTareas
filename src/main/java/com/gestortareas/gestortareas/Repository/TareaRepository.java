package com.gestortareas.gestortareas.Repository;

import com.gestortareas.gestortareas.Entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {
    List<Tarea> findAllByOrderByFechaCreacionDesc();
    List<Tarea> findByCompletada(boolean b);
}