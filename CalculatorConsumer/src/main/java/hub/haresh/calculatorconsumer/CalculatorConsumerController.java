package hub.haresh.calculatorconsumer;

import hub.haresh.common.model.AcknowledgeResponse;
import hub.haresh.common.model.CalculatorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/result")
public class CalculatorConsumerController {

    private final CalculateResponseService service;

    @Autowired
    public CalculatorConsumerController(CalculateResponseService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity getResult(@PathVariable("id") Integer id) {
        CalculatorResponse response;
        try {
            response = service.getResponse(id);
        } catch (CalculateRequestNotFoundException e) {
            return new ResponseEntity<>(new AcknowledgeResponse(e.getMessage(), ""), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(response);
    }
}
