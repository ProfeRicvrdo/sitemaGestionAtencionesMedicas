package cl.duoc.atencionMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.duoc.atencionMS.dto.AtencionDetalleDTO;
import cl.duoc.atencionMS.model.Atencion;
import cl.duoc.atencionMS.service.AtencionService;

@RestController
@RequestMapping("/api/v1/atenciones")
public class AtencionController {

    @Autowired
    private AtencionService service;

    // 🔹 GET: listar todas las atenciones
    @GetMapping
    public ResponseEntity<List<Atencion>> listar() {
        List<Atencion> lista = service.listar();

        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(lista);
    }

    // 🔹 GET: buscar atención por ID (simple)
    @GetMapping("/{id}")
    public ResponseEntity<Atencion> buscar(@PathVariable Integer id) {
        try {
            Atencion atencion = service.buscarPorId(id);
            return ResponseEntity.ok(atencion);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 🔥 GET: detalle completo (MICROSERVICIOS 👀)
    @GetMapping("/{id}/detalle")
    public ResponseEntity<AtencionDetalleDTO> detalle(@PathVariable Integer id) {

        //  Este endpoint:
        // - consulta la BD
        // - llama a Pacientes
        // - llama a Doctores
        // - devuelve todo unido

        try {
            AtencionDetalleDTO dto = service.obtenerDetalle(id);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //  POST: crear nueva atención
    @PostMapping
    public ResponseEntity<Atencion> guardar(@RequestBody Atencion atencion) {
        Atencion nueva = service.guardar(atencion);
        return ResponseEntity.ok(nueva);
    }

  
}