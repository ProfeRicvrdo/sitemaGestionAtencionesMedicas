package cl.duoc.atencionMS.dto;

import lombok.*;

// 🔥 DTO PARA DOCTOR
// Representa los datos que recibimos desde el microservicio de doctores
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {

    // 🔹 Identificador único del doctor
    private Integer id;

    // 🔹 Nombre del doctor
    private String nombre;

    // 🔹 Especialidad médica
    // 👉 Ej: Cardiología, Pediatría, etc.
    private String especialidad;
}