package hub.haresh.calculatorconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"hub.haresh.calculatorconsumer", "hub.haresh.common.model"})
public class CalculatorConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalculatorConsumerApplication.class, args);
    }

}
