package com.example.TareasPendientes;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

// Marca esta clase como un controlador REST
@RestController
public class TareasPendientes {
    // Inyecta automáticamente la dependencia de RestTemplate
    @Autowired
    private RestTemplate restTemplate;
    // Inyecta automáticamente la dependencia de TareasJSON
    @Autowired
    private TareasJSON tareasJSON;

    // Nuevo endpoint para pasar tarea a completadas
    @GetMapping("/pasarTareaACompletadas")
    public String pasarTareaACompletadas(@RequestParam int indice) {
        Tarea tarea = tareasJSON.obtenerTarea(indice);
        if (tarea != null) {
            // Configurar headers para JSON
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            // Crear entidad HTTP con la tarea
            HttpEntity<Tarea> request = new HttpEntity<>(tarea, headers);
            // Enviar tarea al microservicio de tareas completadas (usar puerto interno)
            String respuesta = restTemplate.postForObject(
                "http://tareas-completadas:8081/agregarTareaCompleta",
                request,
                String.class
            );
            // Eliminar la tarea de pendientes
            tareasJSON.eliminarTarea(indice);
            return "Tarea movida a completadas: " + respuesta;
        } else {
            return "Índice inválido";
        }
    }

    // Endpoint para obtener todas las tareas pendientes
    @GetMapping("/TareasPendientes")
    public List<Tarea> TareasPendientes() {
        // Retorna la lista de todas las tareas
        return tareasJSON.obtenerTodasLasTareas();
    }
    // Endpoint para agregar una tarea pendiente (POST)
    @PostMapping("/agregarTareaPendiente")
    // @RequestBody convierte el JSON recibido en un objeto Tarea
    public String agregarTareaPendiente(@RequestBody Tarea tarea) {
        // Agrega la tarea al listado
        tareasJSON.agregarTarea(tarea);
        return "Tarea agregada exitosamente";
    }
    // Endpoint para agregar una tarea pendiente
    @GetMapping("/nuevaTareaPendiente")
    // @RequestBody convierte el JSON recibido en un objeto Tarea
    public String nuevaTareaPendiente(@RequestParam String titulo,@RequestParam String fecha,
                                      @RequestParam String materia,@RequestParam String descripcion) {
        Tarea tarea = new Tarea(titulo,fecha,materia,descripcion);
        // Agrega la tarea al listado
        tareasJSON.agregarTarea(tarea);
        return "Tarea agregada exitosamente";
    }
    // Endpoint para eliminar una tarea completada
    @GetMapping("/eliminarTareaPendiente")
    // @RequestParam extrae el parámetro 'i' de la URL
    public String eliminarTareaPendiente(@RequestParam int i) {
        // Elimina la tarea en el índice especificado
        tareasJSON.eliminarTarea(i);
        return "Tarea eliminada exitosamente";
    }
}