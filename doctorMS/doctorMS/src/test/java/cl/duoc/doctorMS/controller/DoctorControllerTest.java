package cl.duoc.doctorMS.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.http.MediaType;
import static org.mockito.ArgumentMatchers.any;

import cl.duoc.doctorMS.model.Doctor;
import cl.duoc.doctorMS.model.Especialidad;
import cl.duoc.doctorMS.service.DoctorService;

@WebMvcTest(DoctorController.class) // levanta solo la capa web, sin BD
public class DoctorControllerTest {

    @Autowired
    private MockMvc mockMvc; // simula las peticiones HTTP

    @MockBean
    private DoctorService service; // servicio falso

    private Doctor doctorEjemplo;

    @BeforeEach
    void setUp() {
        doctorEjemplo = new Doctor();
        doctorEjemplo.setId(1);
        doctorEjemplo.setNombre("Dr. Juan Pérez");
        doctorEjemplo.setRut("12345678-9");
        doctorEjemplo.setEspecialidad(new Especialidad(1, "Cardiología"));
    }

    @Test
    void listar_retorna200ConDoctores() throws Exception {
        // ARRANGE: creamos la lista falsa
        List<Doctor> listaFalsa = new ArrayList<>();
        listaFalsa.add(doctorEjemplo);

        // le decimos al servicio falso que devuelva esa lista
        when(service.listar()).thenReturn(listaFalsa);

        // ACT + ASSERT: simulamos el GET y verificamos la respuesta
        mockMvc.perform(get("/api/v1/doctores"))
                .andExpect(status().isOk())                              // código HTTP 200
                .andExpect(jsonPath("$[0].nombre").value("Dr. Juan Pérez")); // primer elemento de la lista
    
                //$ : significa la raiz del json|
                //[0] : primer elemento del array
                //.nombre : campo nombre del objeto
    
    }

    @Test
    void listar_retorna204SinDoctores() throws Exception {
        // ARRANGE: lista vacía
        List<Doctor> listaVacia = new ArrayList<>();
        when(service.listar()).thenReturn(listaVacia);

        // ACT + ASSERT: verificamos que retorna 204 sin contenido
        mockMvc.perform(get("/api/v1/doctores"))
                .andExpect(status().isNoContent()); // código HTTP 204
    }


    @Test
    void buscarPorId_retorna200() throws Exception {
        // ARRANGE: el servicio devuelve el doctor
        when(service.buscarPorId(1)).thenReturn(doctorEjemplo);

        // ACT + ASSERT
        mockMvc.perform(get("/api/v1/doctores/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Dr. Juan Pérez"));
    }

    @Test
    void buscarPorId_retorna404() throws Exception {
        // ARRANGE: el servicio lanza excepción
        when(service.buscarPorId(99)).thenThrow(new RuntimeException("Doctor no encontrado"));

        // ACT + ASSERT
        mockMvc.perform(get("/api/v1/doctores/99"))
                .andExpect(status().isNotFound()); // código HTTP 404
    }

    @Test
    void guardar_retorna200() throws Exception {
        // ARRANGE
        when(service.guardar(any(Doctor.class))).thenReturn(doctorEjemplo);

        // ACT + ASSERT
        mockMvc.perform(post("/api/v1/doctores")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"id\":1,\"nombre\":\"Dr. Juan Pérez\",\"rut\":\"12345678-9\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nombre").value("Dr. Juan Pérez"));
    }


    @Test
    void eliminar_retorna204() throws Exception {
        // ARRANGE: el servicio no hace nada (método void)
        doNothing().when(service).eliminar(1);

        // ACT + ASSERT
        mockMvc.perform(delete("/api/v1/doctores/1"))
                .andExpect(status().isNoContent());
    }


    @Test
    void eliminar_retorna404() throws Exception {
        // ARRANGE: el servicio lanza excepción
        doThrow(new RuntimeException("Doctor no existe")).when(service).eliminar(99);

        // ACT + ASSERT
        mockMvc.perform(delete("/api/v1/doctores/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void obtenerDoctorDTO_retorna200() throws Exception {
        // ARRANGE
        when(service.buscarPorId(1)).thenReturn(doctorEjemplo);

        // ACT + ASSERT
        mockMvc.perform(get("/api/v1/doctores/dto/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Dr. Juan Pérez"))
                .andExpect(jsonPath("$.especialidad").value("Cardiología"));
    }
    

}