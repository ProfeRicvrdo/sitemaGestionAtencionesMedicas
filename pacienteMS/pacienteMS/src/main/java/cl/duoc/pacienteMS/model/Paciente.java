package cl.duoc.pacienteMS.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "paciente")
@Schema(description="Representa un paciente en el sistema")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description="ID unico del paciente", examples={"1"})
    private Integer id;

    @Column(nullable = false)
    @Schema(
        description="ROL UNICO TRIBUTARIO del paciente, corresponde a un identificador nacional unico", 
        examples={"12345678-9"}
    )
    private String rut;

    @Column(nullable = false)
    @Schema(description="Nombre de pila del paciente")
    private String nombre;

    @Column(nullable = false)
    @Schema(description="Apellido paterno del paciente")
    private String apellido;

    @Column(nullable = false)
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
