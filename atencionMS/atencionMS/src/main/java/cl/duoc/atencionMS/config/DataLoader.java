package cl.duoc.atencionMS.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cl.duoc.atencionMS.model.TipoAtencion;
import cl.duoc.atencionMS.repository.AtencionRepository;
import cl.duoc.atencionMS.repository.TipoAtencionRepository;

@Configuration
public class DataLoader {


    @Bean
    CommandLineRunner initData(TipoAtencionRepository repoTipo, AtencionRepository repoAtencion) {
        return args -> {

            if (repoTipo.count() > 0 ){
                System.out.println("No se cargan datos de prueba, la bd esta poblada");
            }else{
                TipoAtencion ta = new TipoAtencion();
                ta.setNombre("Ambulatorio");
                






            }


        };
    };

}
