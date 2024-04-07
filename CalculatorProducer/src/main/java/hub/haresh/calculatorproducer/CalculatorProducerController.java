package hub.haresh.calculatorproducer;

import com.fasterxml.jackson.core.JsonProcessingException;
import hub.haresh.common.model.AcknowledgeResponse;
import hub.haresh.common.model.CalculatorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/calculate")
public class CalculatorProducerController {
    private final CalculatorProducerService calculatorProducerService;

    @Autowired
    public CalculatorProducerController(CalculatorProducerService calculatorProducerService) {
        this.calculatorProducerService = calculatorProducerService;
    }

    @PostMapping
    public ResponseEntity<AcknowledgeResponse> addCalculateRequestToQueue(@RequestBody CalculatorRequest request) {

        try {
            calculatorProducerService.sendToQueue(request);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(new AcknowledgeResponse("Bad request format, please check payload again.", ""), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(
                new AcknowledgeResponse("Successfully added the request to queue, please visit below url to get result.",
                        "http://localhost:8081/api/v1/result/" + request.getId()));
    }
}
