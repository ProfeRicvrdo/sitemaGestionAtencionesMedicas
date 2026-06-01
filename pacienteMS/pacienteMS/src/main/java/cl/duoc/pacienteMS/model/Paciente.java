package cl.duoc.pacienteMS.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String rut;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
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
