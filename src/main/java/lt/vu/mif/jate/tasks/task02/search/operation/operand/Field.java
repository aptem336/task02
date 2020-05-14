package lt.vu.mif.jate.tasks.task02.search.operation.operand;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lt.vu.mif.jate.tasks.task02.search.Searchable;
import org.apache.commons.lang3.ArrayUtils;

import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * Field operand to indicate searchable field.
 *
 * @author valdo
 */
@Getter
@RequiredArgsConstructor
public class Field implements Operand {

    private final String name;

    @Override
    public Object getValue(Object object) {
        Class<?> itemClass = object.getClass();
        java.lang.reflect.Field[] fields = new java.lang.reflect.Field[0];
        do {
            fields = ArrayUtils.addAll(fields, itemClass.getDeclaredFields());
            itemClass = itemClass.getSuperclass();
        } while (itemClass.getSuperclass() != null);
        for (java.lang.reflect.Field field : fields) {
            try {
                AccessController.doPrivileged((PrivilegedAction<java.lang.reflect.Field>) () -> {
                    field.setAccessible(true);
                    return null;
                });
                Object fieldValue = field.get(object);
                if (fieldValue != null) {
                    Searchable searchable = field.getAnnotation(Searchable.class);
                    if (searchable != null) {
                        String searchableField = searchable.field();
                        if (searchableField.equals(name)) {
                            return fieldValue;
                        }
                    }
                }
            } catch (IllegalArgumentException | IllegalAccessException ignored) {
            }
        }
        return null;
    }
}
