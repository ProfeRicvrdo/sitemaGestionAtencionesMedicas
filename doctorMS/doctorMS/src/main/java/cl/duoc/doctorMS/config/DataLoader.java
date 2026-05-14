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
    CommandLineRunner initData(DoctorRepository docRepo, EspecialidadRepository espRepo){

        return args -> {

            if (docRepo.count() > 0){
                
                System.out.println("No se insertaron datos porque la bd no esta vacia");

            }else{

                Especialidad espe1 = new Especialidad(null, "Pediatria");
                Especialidad espe2 = new Especialidad(null, "Cardiologia");
                Especialidad espe3 = new Especialidad(null, "Medicina general");

                Doctor doc1 = new Doctor(null, "Juan Perez", "123456-7", espe1);
                Doctor doc2 = new Doctor(null, "Ana Munoz", "78456-4", espe3);
            
                espRepo.save(espe1);
                espRepo.save(espe2);
                espRepo.save(espe3);

                docRepo.save(doc1);
                docRepo.save(doc2);

                System.out.println("Datos cargados con exito");

            }
        };


    }







}
