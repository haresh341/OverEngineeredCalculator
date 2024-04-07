package hub.haresh.common.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "results")
@NoArgsConstructor
@AllArgsConstructor
public class CalculatorResponse {
    @Id
    private Integer id;
    private String expression;
    private double result;
}
