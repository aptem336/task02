package lt.vu.mif.jate.tasks.task02.search.operation;

import lombok.Getter;
import lt.vu.mif.jate.tasks.task02.search.operation.operand.Operand;
import lt.vu.mif.jate.tasks.task02.search.operation.operator.BinaryOperator;

import java.util.Collection;

/**
 * Binary operation, i.e. x = y.
 * Takes 2 operands and Operation.
 *
 * @author valdo
 */
@Getter
public class BinaryOperation extends FinalOperation<BinaryOperator> {

    /**
     * First operand.
     */
    private final Operand operand1;

    /**
     * Second operand.
     */
    private final Operand operand2;

    /**
     * Constructor.
     *
     * @param operator operator.
     * @param operand1 first operand.
     * @param operand2 second operand.
     */
    public BinaryOperation(final BinaryOperator operator, final Operand operand1, final Operand operand2) {
        super(operator);
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public boolean apply(Object o) {
        Object value1 = operand1.getValue(o);
        Object value2 = operand2.getValue(o);
        if (value1 instanceof Collection) {
            return ((Collection<?>) value1).contains(value2);
        } else if (value1 instanceof String) {
            if (value2 instanceof Collection) {
                return ((Collection<?>) value2).stream().anyMatch(
                        value -> (value1.toString()).contains(value.toString()));
            } else if (value2 instanceof String) {
                switch (operator) {
                    case EQUALS:
                        return value1.equals(value2);
                    case MATCHES:
                        return value1.toString().matches(value2.toString());
                    case CONTAINS:
                        return value1.toString().contains(value2.toString());
                }
            }
        }
        return false;
    }
}
