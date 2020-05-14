package lt.vu.mif.jate.tasks.task02.search.operation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Negation implements Operation {

    private final Operation operation;

    @Override
    public boolean apply(Object o) {
        return !operation.apply(o);
    }
}
