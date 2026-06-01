package cl.duoc.pacienteMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.pacienteMS.dto.PacienteDTO;
import cl.duoc.pacienteMS.model.Paciente;
import cl.duoc.pacienteMS.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/pacientes")
@Tag(name = "Pacientes", description = "Operaciones sobre pacientes")
public class PacienteController {

    @Autowired
    private PacienteService service;

    //GET: listar todos
    @GetMapping
    public ResponseEntity<List<Paciente>> listar() {
        List<Paciente> pacientes = service.listarPacientes();

        if (pacientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(pacientes);
    }

    //GET: buscar por ID
    @GetMapping("/{id}")
    @Operation(summary = "Buscar paciente por ID", description= "Retorna un paciente segun el ID proporcionado"
    )
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Integer id) {
        try {
            Paciente paciente = service.buscarPorId(id);
            return ResponseEntity.ok(paciente);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //GET: buscar por RUT
    @GetMapping("/rut/{rut}")
    @Operation(
        summary="Busca un paciente por el RUT", 
        description="Retorna un pacinte segun el RUT proporcionado, debe retornar solo un  paciento dado que el RUT es un indicador unico"
    )
    public ResponseEntity<Paciente> buscarPorRut(@PathVariable String rut) {
        try {
            Paciente paciente = service.buscarPorRut(rut);
            return ResponseEntity.ok(paciente);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //POST: crear paciente completo
    @PostMapping
    @Operation(summary="Crea un paciente nuevo")
    public ResponseEntity<Paciente> guardar(@RequestBody Paciente paciente) {
        Paciente nuevo = service.guardar(paciente);
        return ResponseEntity.ok(nuevo);
    }

    //PUT: actualizar paciente
    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizar(
            @PathVariable Integer id,
            @RequestBody Paciente paciente) {

        try {
            Paciente actualizado = service.actualizar(id, paciente);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //DELETE: eliminar paciente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

     /**
     * 🔥 Método que construye el DTO
     * 
     * 👉 Aquí haces la transformación Entidad → DTO
     */
    @GetMapping("/dto/{id}")
    @Operation(summary="Retorna un Paciente DTO", 
            description="Retorna un paciente DTO segun el id del paciente, este metodo se utiliza para ser llamado desde otros microservicios cuando se necesite los datos de algun paciente"
    )
    public ResponseEntity<PacienteDTO> obtenerPacienteDTO(@PathVariable Integer id) {

    Paciente paciente = service.buscarPorId(id);

    // 🔥 SOLO lo necesario (evitamos dirección)
    PacienteDTO dto = new PacienteDTO(
            paciente.getId(),
            paciente.getNombre(),
            paciente.getApellido()
    );

    return ResponseEntity.ok(dto);
}








}