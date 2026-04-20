package cl.duoc.pacienteMS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
    es como quiero que se comparta el paciente para los otros ms
    no quiero que muestre la direccion ni nada mas solo estos datos
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteDTO {

    private Integer id;
    private String nombre;
    private String apellido;
}