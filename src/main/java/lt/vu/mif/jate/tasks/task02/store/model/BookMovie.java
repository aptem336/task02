package lt.vu.mif.jate.tasks.task02.store.model;

import lombok.Getter;
import lt.vu.mif.jate.tasks.task02.search.Searchable;
import lt.vu.mif.jate.tasks.task02.store.Store;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookMovie extends Item {
    private final Double initialRating;
    private final Integer initialRatingCount;
    @Searchable(field = Store.CATEGORY_FIELD)
    private final Set<String> categories;

    public BookMovie(Builder<? extends BookMovie> builder) {
        super(builder);
        this.initialRating = builder.initialRating;
        this.initialRatingCount = builder.initialRatingCount;
        this.categories = builder.categories;
    }

    @Override
    public Double getRating() {
        return initialRating;
    }

    @Override
    public Integer getRatingsCount() {
        return initialRatingCount;
    }

    public Set<String> getCategories() {
        return categories;
    }

    @Getter
    public abstract static class Builder<I extends Item> extends Item.Builder<I> {
        private final Set<String> categories = new HashSet<>();
        private double initialRating;
        private Integer initialRatingCount;

        public Builder(BigInteger id, String title, String description) {
            super(id, title, description);
        }

        public Builder<I> category(String category) {
            categories.add(category);
            return this;
        }

        public Builder<I> categories(List<Object> categories) {
            for (Object category : categories) category(category.toString());
            return this;
        }

        public Builder<I> rating(double initialRating, int initialRatingCount) {
            this.initialRating = initialRating;
            this.initialRatingCount = initialRatingCount;
            return this;
        }
    }
}
