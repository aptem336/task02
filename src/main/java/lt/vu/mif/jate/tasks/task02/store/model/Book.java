/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.vu.mif.jate.tasks.task02.store.model;

import lt.vu.mif.jate.tasks.task02.search.Searchable;
import lt.vu.mif.jate.tasks.task02.store.Store;
import org.json.JSONArray;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Book builder.
 *
 * @author Andrius
 */
public class Book extends BookMovie {

    @Searchable(field = Store.SUBTITLE_FIELD)
    private final String subtitle;
    @Searchable(field = Store.PUBLISHER_FIELD)
    private final String publisher;
    private final Integer pages;

    @Searchable(field = Store.AUTHOR_FIELD)
    private final Set<String> authors;

    protected Book(Book.Builder builder) {
        super(builder);
        this.subtitle = builder.subtitle;
        this.publisher = builder.publisher;
        this.pages = builder.pages;
        this.authors = builder.authors;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public Set<String> getAuthors() {
        return authors;
    }

    public Integer getPages() {
        return pages;
    }

    public String getPublisher() {
        return publisher;
    }

    public static class Builder extends BookMovie.Builder<Book> {

        private final Set<String> authors = new HashSet<>();
        private String subtitle;
        private String publisher;
        private Integer pages;

        public Builder(BigInteger id, String title, String description) {
            super(id, title, description);
        }

        public Builder category(String category) {
            return (Builder) super.category(category);
        }

        public Builder categories(JSONArray categories) {
            return (Builder) super.categories(categories.toList());
        }

        public Builder rating(double initialRating, int initialRatingCount) {
            return (Builder) super.rating(initialRating, initialRatingCount);
        }

        public Builder subtitle(String subtitle) {
            this.subtitle = subtitle;
            return this;
        }

        public Builder publisher(String publisher) {
            this.publisher = publisher;
            return this;
        }

        public Builder pages(Integer pages) {
            this.pages = pages;
            return this;
        }

        public Builder author(String author) {
            authors.add(author);
            return this;
        }

        public Builder authors(JSONArray authorJsonArray) {
            for (Object author : authorJsonArray) authors.add(author.toString());
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }
}
