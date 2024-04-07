package hub.haresh.calculatorproducer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hub.haresh.common.model.CalculatorRequest;
import jakarta.jms.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class CalculatorProducerService {

    @Value("${spring.activemq.queue}")
    String queue;

    private final JmsTemplate jmsTemplate;

    @Autowired
    public CalculatorProducerService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendToQueue(CalculatorRequest request) throws JsonProcessingException {
        request.setId(request.hashCode());
        String jsonObj = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(request);
        jmsTemplate.send(queue, messageCreator -> {
            TextMessage message = messageCreator.createTextMessage();
            message.setText(jsonObj);
            return message;
        });
    }
}
