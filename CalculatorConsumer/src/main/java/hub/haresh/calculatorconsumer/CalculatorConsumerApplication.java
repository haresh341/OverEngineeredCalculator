package hub.haresh.calculatorconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@EntityScan(basePackages = {"hub.haresh.calculatorconsumer", "hub.haresh.common.model"})
public class CalculatorConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalculatorConsumerApplication.class, args);
    }

}
