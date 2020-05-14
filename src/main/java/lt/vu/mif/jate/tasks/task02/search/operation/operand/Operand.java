package lt.vu.mif.jate.tasks.task02.search.operation.operand;

/**
 * Operand super type.
 * @author valdo
 */
public interface Operand {

    String getName();

    Object getValue(Object object);
}
