package lt.vu.mif.jate.tasks.task02.search.operation;

import lombok.Getter;
import lt.vu.mif.jate.tasks.task02.search.operation.operand.Operand;
import lt.vu.mif.jate.tasks.task02.search.operation.operator.SingularOperator;

/**
 * Singular Operation that takes one operand, i.e. isnull(x)
 *
 * @author valdo
 */
@Getter
public class SingularOperation extends FinalOperation<SingularOperator> {
    /**
     * Singular operand.
     */
    private final Operand operand;

    /**
     * Constructor.
     *
     * @param operator singular operator.
     * @param operand  single operand.
     */
    public SingularOperation(final SingularOperator operator, final Operand operand) {
        super(operator);
        this.operand = operand;
    }

    @Override
    public boolean apply(Object o) {
        Object operandFieldValue = operand.getValue(o);
        switch (operator) {
            case NULL:
                return operandFieldValue == null;
            case EMPTY:
                return operandFieldValue != null && operandFieldValue.equals("");
        }
        return false;
    }
}
