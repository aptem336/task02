package lt.vu.mif.jate.tasks.task02.search.operation;

/**
 * Disjunction operation.
 *
 * @author valdo
 */
public class Disjunction extends Junction {

    @Override
    public boolean execute(Object o) {
        for (Operation operation : getOperations()) if (operation.execute(o)) return true;
        return false;
    }
}
