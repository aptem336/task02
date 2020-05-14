/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.vu.mif.jate.tasks.task02.store.model;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class Movie extends BookMovie {

    public Movie(BookMovie.Builder<? extends BookMovie> builder) {
        super(builder);
    }

    public static class Builder extends BookMovie.Builder<Movie> {

        public Builder(BigInteger id, String title, String description) {
            super(id, title, description);
        }

        public Builder category(String category) {
            return (Builder) super.category(category);
        }

        public Builder categories(String categoryPath) {
            return (Builder) super.categories(Arrays.asList(categoryPath.split("/")));
        }

        public Builder rating(double initialRating, int initialRatingCount) {
            return (Builder) super.rating(initialRating, initialRatingCount);
        }

        public Movie build() {
            return new Movie(this);
        }
    }
}
