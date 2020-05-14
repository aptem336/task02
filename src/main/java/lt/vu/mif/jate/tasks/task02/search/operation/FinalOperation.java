package lt.vu.mif.jate.tasks.task02.search.operation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lt.vu.mif.jate.tasks.task02.search.operation.operator.Operator;

/**
 * Leaf Operation.
 *
 * @param <O> operator class.
 * @author valdo
 */
@Getter
@RequiredArgsConstructor
public abstract class FinalOperation<O extends Operator> implements Operation {

    /**
     * Operation operator.
     */
    public final O operator;
}
