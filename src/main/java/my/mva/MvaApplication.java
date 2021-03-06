package my.mva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class MvaApplication extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(MvaApplication.class);
  }

	public static void main(String[] args) {
		SpringApplication.run(MvaApplication.class, args);
	}
}
// vim: et:sta:ai:ts=2:sw=2:fen:fdm=indent:
