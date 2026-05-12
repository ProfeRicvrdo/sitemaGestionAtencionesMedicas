package cl.duoc.pacienteMS.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cl.duoc.pacienteMS.model.Contacto;
import cl.duoc.pacienteMS.model.Direccion;
import cl.duoc.pacienteMS.model.Paciente;
import cl.duoc.pacienteMS.repository.ContactoRepository;
import cl.duoc.pacienteMS.repository.DireccionRepository;
import cl.duoc.pacienteMS.repository.PacienteRepository;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initData(PacienteRepository pacienteRepository, ContactoRepository contactoRepository, DireccionRepository direccionRepository) {

        return args -> {
        
            // Aquí puedes cargar datos de ejemplo para pacientes si lo deseas

            Paciente paciente1 = new Paciente(null, "12345678-9", "Juan","Pérez", 33, null, null);
            Paciente paciente2 = new Paciente(null, "98765432-1", "María","González", 28, null, null);
            Paciente paciente3 = new Paciente(null, "11111111-1", "Carlos","Sánchez", 45, null, null);


            Contacto contacto1 = new Contacto(null, "987654321", "correo@correo.cl", paciente1);
            Contacto contacto2 = new Contacto(null, "123456789", "correo2@correo.cl", paciente2);
            Contacto contacto3 = new Contacto(null, "555555555", "correo3@correo.cl", paciente3);

            Direccion direccion1 = new Direccion(null, "Calle Falsa", "123","Santiago", "Santiago", paciente1);
            Direccion direccion2 = new Direccion(null, "Avenida Siempre Viva", "456","Valparaíso", "Valparaíso", paciente2);
            Direccion direccion3 = new Direccion(null, "Calle Principal", "789","Concepción", "Concepción", paciente3);

            paciente1.setDireccion(direccion1);
            paciente2.setDireccion(direccion2);
            paciente3.setDireccion(direccion3);

            paciente1.setContacto(contacto1);
            paciente2.setContacto(contacto2);
            paciente3.setContacto(contacto3);   

            pacienteRepository.save(paciente1);
            pacienteRepository.save(paciente2);
            pacienteRepository.save(paciente3);

            contactoRepository.save(contacto1);
            contactoRepository.save(contacto2);
            contactoRepository.save(contacto3);
            
            direccionRepository.save(direccion1);
            direccionRepository.save(direccion2);
            direccionRepository.save(direccion3);

            System.out.println("Datos de pacientes cargados exitosamente.");

            
        };
    }

}
