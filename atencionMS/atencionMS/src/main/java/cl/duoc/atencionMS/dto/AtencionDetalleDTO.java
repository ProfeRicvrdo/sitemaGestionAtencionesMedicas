package cl.duoc.atencionMS.dto;

import java.util.Date;
import lombok.*;

// 🔥 DTO PRINCIPAL:
// Representa la información COMPLETA de una atención
// que será enviada al cliente (frontend o API consumer)
//
// 👉 Combina datos de:
// - Nuestra BD (tipoAtencion)
// - Otros microservicios (paciente, doctor)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtencionDetalleDTO {

    // 🔹 ID de la atención (propio de este microservicio)
    private Integer id;

    // 🔹 Fecha en que se realizó la atención
    private Date fechaAtencion;

    // 🔹 Diagnóstico entregado por el doctor
    private String diagnostico;

    // 🔥 DATOS EXTERNOS (microservicios)
    // 👉 Estos NO están en nuestra base de datos
    // 👉 Se obtienen usando Feign

    // 🔹 Información del paciente (desde pacienteMS)
    private PacienteDTO paciente;

    // 🔹 Información del doctor (desde doctorMS)
    private DoctorDTO doctor;

    // 🔥 DATOS INTERNOS (misma BD)
    // 👉 Esto sí viene de nuestra base de datos
    private TipoAtencionDTO tipoAtencion;
}