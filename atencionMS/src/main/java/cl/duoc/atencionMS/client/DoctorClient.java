package cl.duoc.atencionMS.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import cl.duoc.atencionMS.dto.DoctorDTO;

// @FeignClient:
// Ahora que usamos Eureka, removemos el atributo 'url'.
// Spring Cloud OpenFeign buscará automáticamente el nombre "doctorMS"
// dentro del servidor de descubrimiento para enrutar la petición.
@FeignClient(name = "doctorMS") 
public interface DoctorClient {

    // Este método representa una llamada HTTP GET dinámica.
    //
    // Equivale a hacer esto de forma dinámica:
    // GET http://[IP_EUREKA_DOCTOR]:[PUERTO_EUREKA_DOCTOR]/api/v1/doctores/dto/{id}
    //
    // @PathVariable("id"):
    // Se usa para reemplazar {id} en la URL dinámica.
    @GetMapping("/api/v1/doctores/dto/{id}")
    DoctorDTO obtenerDoctor(@PathVariable("id") Integer id);
}