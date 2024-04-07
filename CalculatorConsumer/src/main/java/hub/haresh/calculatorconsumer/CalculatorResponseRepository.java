package hub.haresh.calculatorconsumer;

import hub.haresh.common.model.CalculatorResponse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculatorResponseRepository extends CrudRepository<CalculatorResponse, Integer> {

}
