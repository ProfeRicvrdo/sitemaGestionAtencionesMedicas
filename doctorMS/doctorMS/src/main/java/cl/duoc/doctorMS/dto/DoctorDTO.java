package cl.duoc.doctorMS.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {
    private Integer id;
    private String nombre;
    private String especialidad; // solo el nombre, no el objeto completo
}