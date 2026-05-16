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

        return args ->{


            if (docRepo.count()>0){
            System.out.println("No insertamos datos porque ya hay jijij");
            
            }else{

            Especialidad esp1 = new Especialidad(null, "Cardiologia");
            Especialidad esp2 = new Especialidad(null, "Pedriatria");
            Especialidad esp3 = new Especialidad(null,"Medicina general");

            espRepo.save(esp1);
            espRepo.save(esp2);
            espRepo.save(esp3);

            Doctor doc1 = new Doctor(null, "Juan Perez", "123123-8",esp1 );
            
            Doctor doc2 = new Doctor(null, "Juana Perez", "123123-5",esp1 );
            
            Doctor doc3 = new Doctor(null, "Juanco Pereira", "123123-2",esp2 );

            docRepo.save(doc1);
            docRepo.save(doc2);
            docRepo.save(doc3);
            
            System.out.println("Datos cargados con exito :)");
            }
            
            




        };

    }




}
