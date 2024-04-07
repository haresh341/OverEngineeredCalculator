package hub.haresh.common.model;

import lombok.Data;

import java.util.Objects;

@Data
public class CalculatorRequest {
    private Integer id;
    private Double operator1;
    private Double operator2;
    private Operation operation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CalculatorRequest that)) return false;
        return Objects.equals(getOperator1(), that.getOperator1()) && Objects.equals(getOperator2(), that.getOperator2()) && getOperation() == that.getOperation();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOperator1(), getOperator2(), getOperation());
    }
}
