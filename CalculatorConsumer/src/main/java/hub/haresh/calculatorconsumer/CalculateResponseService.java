package hub.haresh.calculatorconsumer;

import hub.haresh.common.model.CalculatorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CalculateResponseService {

    private static final Map<Integer, CalculatorResponse> localCache = new HashMap<>();
    private final CalculatorResponseRepository calculatorResponseRepository;

    @Autowired
    public CalculateResponseService(CalculatorResponseRepository calculatorResponseRepository) {
        this.calculatorResponseRepository = calculatorResponseRepository;
    }

    public CalculatorResponse getResponse(Integer id) throws CalculateRequestNotFoundException {
        if (localCache.containsKey(id)) {
            return localCache.get(id);
        }
        Optional<CalculatorResponse> calculatorResponse = calculatorResponseRepository.findById(id);
        if (calculatorResponse.isEmpty()) {
            throw new CalculateRequestNotFoundException("Calculate request not found with id " + id);
        }
        // put response in cache
        CalculatorResponse response = calculatorResponse.get();
        localCache.put(id, response);
        return response;
    }
}
