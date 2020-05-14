package lt.vu.mif.jate.tasks.task02.search.operation.operand;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Literal operand.
 *
 * @author valdo
 */
@Getter
@RequiredArgsConstructor
public class Literal implements Operand {

    private final String name;

    @Override
    public Object getValue(Object object) {
        return getName();
    }

    @Override
    public String toString() {
        return getName();
    }
}
