package hub.haresh.calculatorconsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hub.haresh.common.model.CalculatorRequest;
import hub.haresh.common.model.CalculatorResponse;
import hub.haresh.common.model.Operation;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;

import org.springframework.stereotype.Component;

@Component
public class CalculateRequestListener {

    private final ObjectMapper objectMapper;
    private final CalculatorResponseRepository repository;

    @Autowired
    public CalculateRequestListener(ObjectMapper objectMapper, CalculatorResponseRepository repository) {
        this.objectMapper = objectMapper;
        this.repository = repository;
    }

    @JmsListener(destination = "calculationQueue")
    public String receiveCalculateRequestAndStoreResult(final Message calculatorRequest) throws JMSException {
        String messageData = null;
        System.out.println("Received Calculator request " + calculatorRequest);
        if (calculatorRequest instanceof TextMessage textMessage) {
            messageData = textMessage.getText();
        }
        try {
            CalculatorRequest request = objectMapper.readValue(messageData, CalculatorRequest.class);
            calculateResultAndStore(request);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return messageData;
    }

    private void calculateResultAndStore(CalculatorRequest request) {
        double op1 = request.getOperator1();
        double op2 = request.getOperator2();

        Operation operation = request.getOperation();
        String expression = null;
        double result = 0;
        switch (operation) {
            case ADDITION -> {
                expression = op1 + " + " + op2;
                result = op1 + op2;
            }
            case SUBTRACTION -> {
                expression = op1 + " - " + op2;
                result = op1 - op2;
            }
            case DIVISION -> {
                expression = op1 + " / " + op2;
                result = op1 / op2;
            }
            case MULTIPLICATION -> {
                expression = op1 + " * " + op2;
                result = op1 * op2;
            }
        }

        CalculatorResponse response = new CalculatorResponse(request.getId(), expression, result);
        repository.save(response);
    }

}
