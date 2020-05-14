package lt.vu.mif.jate.tasks.task02.search;

import lt.vu.mif.jate.tasks.task02.search.operation.Operation;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Search Manager to be extended by domain class.
 *
 * @param <T> collection element type.
 * @author valdo
 */
public abstract class SearchManager<T extends Comparable<T>> {
    /**
     * Get collection of elements to search in.
     *
     * @return collection.
     */
    protected abstract Collection<T> getSearchItems();

    /**
     * Search for items in collection.
     *
     * @param operation operation for the search.
     * @return found elements.
     */
    public final SortedSet<T> findItems(final Operation operation) {
        return getSearchItems().stream().filter(operation::apply).collect(Collectors.toCollection(TreeSet::new));
    }

    /**
     * Return the list of unique values indicated by the keywords.
     *
     * @param name fieldNameAnnotation to search.
     * @return set of values.
     */
    public final Set<String> getValueList(final String name) {
        Set<String> result = new HashSet<>();
        for (T item : getSearchItems()) {
            Object fieldValue = new lt.vu.mif.jate.tasks.task02.search.operation.operand.Field(name).getValue(item);
            if (fieldValue instanceof Set) {
                for (String setValue : (Set<String>) fieldValue) {
                    result.add(setValue.matches("[A-Z]*") ? setValue.substring(0, 1).toUpperCase(Locale.ROOT)
                            + setValue.toLowerCase(Locale.ROOT).substring(1) : setValue);
                }
            } else if (fieldValue != null) {
                result.add(fieldValue.toString());
            }
        }
        return result;
    }
}
