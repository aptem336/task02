package lt.vu.mif.jate.tasks.task02.search.operation;

/**
 * Conjunction.
 *
 * @author valdo
 */
public class Conjunction extends Junction {

    @Override
    public boolean apply(Object o) {
        return getOperations().stream().noneMatch(operation -> !operation.apply(o));
    }
}
