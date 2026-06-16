package cl.duoc.doctorMS.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import cl.duoc.doctorMS.model.Doctor;
import cl.duoc.doctorMS.model.Especialidad;
import cl.duoc.doctorMS.repository.DoctorRepository;

@ExtendWith(MockitoExtension.class) // sin Spring, solo Mockito
public class DoctorServiceTest {

    @Mock
    private DoctorRepository doctorRepository; // repositorio simulado

    @InjectMocks
    private DoctorService doctorService; // el servicio REAL con el repo simulado inyectado

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
    void listar_retornaListaConDoctores() {
        // ARRANGE: creamos la lista manualmente
        List<Doctor> listaFalsa = new ArrayList<>();
        listaFalsa.add(doctorEjemplo);

        // le decimos al repositorio falso que devuelva esa lista cuando se llame findAll()
        when(doctorRepository.findAll()).thenReturn(listaFalsa);

        // ACT: llamamos al método real del servicio
        List<Doctor> resultado = doctorService.listar();

        // ASSERT: verificamos el resultado
        assertEquals(1, resultado.size());
        assertEquals("Dr. Juan Pérez", resultado.get(0).getNombre());
    }

    //test del metodo buscarPorId en caso que encuentre el doctor
    @Test
    void buscarPorId_encontrado() {
        // ARRANGE: el repositorio devuelve un Optional con el doctor
        Optional<Doctor> optionalDoctor = Optional.of(doctorEjemplo);
        when(doctorRepository.findById(1)).thenReturn(optionalDoctor);

        // ACT: llamamos al método real
        Doctor resultado = doctorService.buscarPorId(1);

        // ASSERT: verificamos que es el doctor correcto
        assertEquals(1, resultado.getId());
        assertEquals("Dr. Juan Pérez", resultado.getNombre());
    }


    // test del metodo buscarPorId en caso que no encuentre el doctor
    @Test
    void buscarPorId_noEncontrado() {
        // ARRANGE: el repositorio devuelve un Optional vacío
        Optional<Doctor> optionalVacio = Optional.empty();
        when(doctorRepository.findById(99)).thenReturn(optionalVacio);

        // ACT + ASSERT: verificamos que lanza la excepción correcta
        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            doctorService.buscarPorId(99);
        });

        assertEquals("Doctor no encontrado", ex.getMessage());
    }



    @Test
    void guardar_retornaDocterGuardado() {
        // ARRANGE: el repositorio devuelve el doctor al guardarlo
        when(doctorRepository.save(doctorEjemplo)).thenReturn(doctorEjemplo);

        // ACT
        Doctor resultado = doctorService.guardar(doctorEjemplo);

            // ASSERT
        assertEquals("Dr. Juan Pérez", resultado.getNombre());
        verify(doctorRepository, times(1)).save(doctorEjemplo); // verificamos que save fue llamado
    }

    @Test
    void eliminar_exitoso() {
        // ARRANGE: el doctor existe
        when(doctorRepository.existsById(1)).thenReturn(true);

        // ACT + ASSERT: no debe lanzar excepción
        assertDoesNotThrow(() -> doctorService.eliminar(1));

        // verificamos que deleteById fue llamado exactamente una vez
        verify(doctorRepository, times(1)).deleteById(1);
    }

    @Test
    void eliminar_noExiste() {
        // ARRANGE: el doctor no existe
        when(doctorRepository.existsById(99)).thenReturn(false);

        // ACT + ASSERT: debe lanzar excepción
        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            doctorService.eliminar(99);
        });

        assertEquals("Doctor no existe", ex.getMessage());

        // verificamos que deleteById NUNCA fue llamado
        verify(doctorRepository, never()).deleteById(99);
    }

    // SI LLEGASTE HASTA AQUI, HOLA
    // LLEVO COMO 1223343 DIAS HACIENDO ESTE PORYECTO A MANO 

}
 