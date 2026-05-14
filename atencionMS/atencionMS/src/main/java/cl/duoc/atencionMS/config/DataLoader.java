package cl.duoc.atencionMS.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cl.duoc.atencionMS.model.Atencion;
import cl.duoc.atencionMS.model.TipoAtencion;
import cl.duoc.atencionMS.repository.AtencionRepository;
import cl.duoc.atencionMS.repository.TipoAtencionRepository;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initData(TipoAtencionRepository tipoAtencionRepository, AtencionRepository atencionRepository ) {
        return args -> {
            
            TipoAtencion tipoAtencion1 = new TipoAtencion(null,"Consulta General");
            TipoAtencion tipoAtencion2 = new TipoAtencion(null,s"Control de Enfermedad Crónica");


            Atencion atencion1 = new Atencion(null, "Consulta General", "Consulta médica general para evaluación de síntomas.", null);




            System.out.println("Datos de atenciones cargados exitosamente.");
        };
    }





}
