package cl.duoc.atencionMS.dto;

import lombok.*;

// 🔥 DTO PARA TIPO DE ATENCIÓN
// Representa un dato interno (misma BD)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoAtencionDTO {

    // 🔹 ID del tipo de atención
    private Integer id;

    // 🔹 Nombre del tipo de atención
    // 👉 Ej: Urgencia, Control, Consulta general
    private String nombre;
}