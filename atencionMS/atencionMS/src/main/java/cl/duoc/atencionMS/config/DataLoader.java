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
            
            




        };
    }





}
