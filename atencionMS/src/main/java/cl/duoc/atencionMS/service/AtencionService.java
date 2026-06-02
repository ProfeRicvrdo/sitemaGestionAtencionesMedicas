package cl.duoc.atencionMS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.atencionMS.client.DoctorClient;
import cl.duoc.atencionMS.client.PacienteClient;
import cl.duoc.atencionMS.dto.*;
import cl.duoc.atencionMS.model.Atencion;
import cl.duoc.atencionMS.model.TipoAtencion;
import cl.duoc.atencionMS.repository.AtencionRepository;

@Service
public class AtencionService {



    @Autowired
    private AtencionRepository repository;

    @Autowired
    private PacienteClient pacienteClient;

    @Autowired
    private DoctorClient doctorClient;

    // =========================================
    // 🔹 CRUD BÁSICO
    // =========================================

    public List<Atencion> listar() {
        return repository.findAll();
    }

    // 🔥 Forma simple (SIN validar microservicios)
    public Atencion guardar(Atencion atencion) {
        return repository.save(atencion);
    }

    // 🔥 Forma PRO (validando contra microservicios)
    public Atencion guardarV2(Atencion atencion) {

        // 🔹 Validar paciente
        PacienteDTO paciente = pacienteClient.obtenerPaciente(atencion.getPacienteId());

        if (paciente == null) {
            throw new RuntimeException("Paciente no existe");
        }

        
        // 🔹 Validar doctor
        DoctorDTO doctor = doctorClient.obtenerDoctor(atencion.getDoctorId());

        if (doctor == null) {
            throw new RuntimeException("Doctor no existe");
        }

        return repository.save(atencion);
    }

    public Atencion buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Atención no encontrada"));
    }

    // =========================================
    // 🔥 MÉTODO CLAVE (DETALLE COMPLETO)
    // =========================================

    public AtencionDetalleDTO obtenerDetalle(Integer id) {

        // 1 BD local
        Atencion atencion = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Atención no encontrada"));

        // 2 Paciente
        PacienteDTO paciente = pacienteClient.obtenerPaciente(atencion.getPacienteId());

        if (paciente == null) {
            throw new RuntimeException("Paciente no encontrado");
        }

        // 3️ Doctor
        DoctorDTO doctor = doctorClient.obtenerDoctor(atencion.getDoctorId());

        if (doctor == null) {
            throw new RuntimeException("Doctor no encontrado");
        }

        // 4️ Tipo atención (local)
        TipoAtencion tipo = atencion.getTipoAtencion();

        TipoAtencionDTO tipoDTO = new TipoAtencionDTO(
                tipo.getId(),
                tipo.getNombre()
        );

        // 5️ Armar DTO final
        AtencionDetalleDTO dto = new AtencionDetalleDTO();

        dto.setId(atencion.getId());
        dto.setFechaAtencion(atencion.getFechaAtencion());
        dto.setDiagnostico(atencion.getDiagnostico());

        dto.setPaciente(paciente);
        dto.setDoctor(doctor);
        dto.setTipoAtencion(tipoDTO);

        return dto;
    }
}