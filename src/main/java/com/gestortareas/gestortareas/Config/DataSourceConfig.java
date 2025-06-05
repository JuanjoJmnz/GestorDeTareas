package com.gestortareas.gestortareas.Config;

import com.gestortareas.gestortareas.Entity.Tarea;
import com.gestortareas.gestortareas.Repository.TareaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSourceConfig implements CommandLineRunner {

    private final TareaRepository tareaRepo;

    public DataSourceConfig(TareaRepository tareaRepo) {
        this.tareaRepo = tareaRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        Tarea tarea = Tarea.builder()
                .titulo("Prueba desde CommandLineRunner")
                .descripcion("Descripción prueba")
                .completada(false)
                .build();

        tareaRepo.save(tarea);
        System.out.println("Insertada tarea de prueba.");
    }
}