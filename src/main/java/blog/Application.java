package blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by bbn on 31/03/17.
 */
@SpringBootApplication /* implies @Configuration, @EnableAutoConfiguration and @ComponentScan  */
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class);
  }

}

