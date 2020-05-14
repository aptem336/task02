package lt.vu.mif.jate.tasks.task02.store.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lt.vu.mif.jate.tasks.task02.search.Searchable;
import lt.vu.mif.jate.tasks.task02.store.Store;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Item class.
 *
 * @author valdo
 */
@Getter
@RequiredArgsConstructor
public abstract class Item implements Comparable<Item> {

    private final BigInteger id;
    private final Set<Integer> ratings = new HashSet<>();
    @Searchable(field = Store.TITLE_FIELD)
    private final String title;
    @Searchable(field = Store.DESCRIPTION_FIELD)
    private final String description;

    protected Item(Builder<? extends Item> builder) {
        this(builder.id, builder.title, builder.description);
    }

    public Double getRating() {
        if (ratings.size() <= 0) return 0.0;
        return ratings.stream().mapToDouble(val -> val).average().orElse(Double.NaN);
    }

    public void addRatingValue(int ratingValue)
            throws WrongRatingException {
        if (ratingValue <= 0 || ratingValue >= 6) {
            throw new WrongRatingException(ratingValue);
        }
        ratings.add(ratingValue);
    }

    public Integer getRatingsCount() {
        return ratings.size();
    }

    @Override
    public final int compareTo(final Item item) {
        return item.title.compareTo(title) != 0 ? item.title.compareTo(title) : item.id.compareTo(id);
    }

    @Override
    public final String toString() {
        return String.format("Item(id=%d)", id);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Item) {
            Item item = (Item) o;
            return item.id.equals(this.id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }

    @RequiredArgsConstructor
    public static abstract class Builder<I extends Item> {
        private final BigInteger id;
        private final String title;
        @Searchable(field = Store.DESCRIPTION_FIELD)
        private final String description;

        public abstract I build();
    }
}
