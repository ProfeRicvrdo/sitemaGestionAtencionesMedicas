package cl.duoc.atencionMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication
public class AtencionMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtencionMsApplication.class, args);
	}

}
