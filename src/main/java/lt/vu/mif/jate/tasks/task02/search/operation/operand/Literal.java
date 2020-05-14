package lt.vu.mif.jate.tasks.task02.search.operation.operand;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Literal operand.
 *
 * @author valdo
 */
@RequiredArgsConstructor
public class Literal implements Operand {

    private final String name;

    @Override
    public Object getValue(Object object) {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
