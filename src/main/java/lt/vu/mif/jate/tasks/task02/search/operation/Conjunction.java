package lt.vu.mif.jate.tasks.task02.search.operation;

/**
 * Conjunction.
 *
 * @author valdo
 */
public class Conjunction extends Junction {

    @Override
    public boolean execute(Object o) {
        for (Operation operation : getOperations()) if (!operation.execute(o)) return false;
        return true;
    }
}
