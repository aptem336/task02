package lt.vu.mif.jate.tasks.task02.search.operation;

/**
 * Disjunction.
 *
 * @author valdo
 */
public class Disjunction extends Junction {

    @Override
    public boolean apply(Object o) {
        return getOperations().stream().anyMatch(operation -> operation.apply(o));
    }
}
