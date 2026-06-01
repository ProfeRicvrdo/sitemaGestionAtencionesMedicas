package cl.duoc.atencionMS.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import cl.duoc.atencionMS.dto.PacienteDTO;

// 🚀 @FeignClient:
// Removimos el atributo 'url' para que use el descubrimiento dinámico.
// OpenFeign buscará el nombre "pacienteMS" registrado en el Servidor Eureka.
@FeignClient(name = "pacienteMS")
public interface PacienteClient {

    // Este método representa una llamada HTTP GET dinámica.
    //
    // Equivale a resolver dinámicamente:
    // GET http://[IP_PACIENTE]:[PUERTO_PACIENTE]/api/v1/pacientes/dto/{id}
    //
    // Retorna un PacienteDTO con los datos del paciente obtenidos del MS Pacientes.
    @GetMapping("/api/v1/pacientes/dto/{id}")
    PacienteDTO obtenerPaciente(@PathVariable("id") Integer id);
}