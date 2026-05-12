package cl.duoc.doctorMS.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cl.duoc.doctorMS.model.Doctor;
import cl.duoc.doctorMS.model.Especialidad;
import cl.duoc.doctorMS.repository.DoctorRepository;
import cl.duoc.doctorMS.repository.EspecialidadRepository;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner initData(DoctorRepository doctorRepository, EspecialidadRepository especialidadRepository) {
        return args -> {
            if (especialidadRepository.count() == 0) {
                
                Especialidad cardiologia = especialidadRepository.save(new Especialidad(null, "Cardiología"));
                Especialidad neurologia = especialidadRepository.save(new Especialidad(null, "Neurología"));
                Especialidad pediatria = especialidadRepository.save(new Especialidad(null, "Pediatría"));
                
                especialidadRepository.save(cardiologia);
                especialidadRepository.save(neurologia);
                especialidadRepository.save(pediatria);

                Doctor doctor1 = new Doctor(null, "Dr. Juan Pérez", "12345678-9", cardiologia);
                Doctor doctor2 = new Doctor(null, "Dra. María González", "98765432-1", neurologia);
                Doctor doctor3 = new Doctor(null, "Dr. Carlos López", "11223344-5", pediatria);
                
                doctorRepository.save(doctor1);
                doctorRepository.save(doctor2);
                doctorRepository.save(doctor3);

                System.out.println("Datos de especialidades y doctores cargados exitosamente.");
            }else{
                System.out.println("Datos de especialidades y doctores ya existen. No se cargarán datos de ejemplo.");
            }
        };
    }


}
