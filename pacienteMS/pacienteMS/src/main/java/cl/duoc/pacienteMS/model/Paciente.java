package cl.duoc.pacienteMS.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "paciente")
@Schema(description = "Representa un paciente en el sistema médico")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único que se auto genera en la bd", example = "1")
    private Integer id;

    @Column(nullable = false)
    @Schema(description = "RUT del paciente", example = "12345678-9")   
    private String rut;

    @Column(nullable = false)
    @Schema(description = "Nombre del paciente", example = "Juan")
    private String nombre;

    @Column(nullable = false)
    @Schema(description = "Apellido del paciente", example = "Pérez")
    private String apellido;

    @Column(nullable = false)
    @Schema(description = "Edad del paciente", example = "30")
    private Integer edad;

    // Relación con Dirección
    @OneToOne(mappedBy = "paciente", cascade = CascadeType.ALL)
    @JsonBackReference   // elimina la serialización para evitar ciclos infinitos
    private Direccion direccion;

    // Relación con Contacto
    @OneToOne(mappedBy = "paciente", cascade = CascadeType.ALL)
    @JsonManagedReference // permite la serialización del contacto dentro del paciente
    private Contacto contacto;

    

}
