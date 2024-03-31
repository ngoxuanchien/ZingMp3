package hcmus.zingmp3;


import com.netflix.discovery.converters.Auto;
import org.hibernate.cfg.Environment;
import org.hibernate.metamodel.mapping.TableDetails;
import org.keycloak.admin.client.Keycloak;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }


}
