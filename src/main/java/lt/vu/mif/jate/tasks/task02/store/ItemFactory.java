/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.vu.mif.jate.tasks.task02.store;

import lt.vu.mif.jate.tasks.task02.store.model.Book;
import lt.vu.mif.jate.tasks.task02.store.model.Movie;
import lt.vu.mif.jate.tasks.task02.store.model.WrongItemFormatException;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * @author Andrius
 */
public class ItemFactory {

    private ItemFactory() {
    }

    public static Book createBook(JSONObject bookJsonObject) {
        if (!bookJsonObject.has("isbn")) {
            throw new WrongItemFormatException();
        }
        return new Book.Builder(
                bookJsonObject.getBigInteger("isbn"),
                bookJsonObject.getString("title"),
                bookJsonObject.getString("description"))
                .categories(bookJsonObject.getJSONArray("categories"))
                .rating(bookJsonObject.getDouble("averageRating"), bookJsonObject.getInt("ratingsCount"))
                .authors(bookJsonObject.getJSONArray("authors"))
                .pages(bookJsonObject.getInt("pageCount"))
                .publisher(bookJsonObject.getString("publisher"))
                .build();
    }

    public static Movie createMovie(JSONObject movieJsonObject) {
        if (!movieJsonObject.has("id")) {
            throw new WrongItemFormatException();
        }
        return new Movie.Builder(movieJsonObject.getBigInteger("id"),
                movieJsonObject.getString("name"),
                movieJsonObject.getString("longDescription"))
                .categories(movieJsonObject.getString("categoryPath"))
                .rating(movieJsonObject.getDouble("customerRating"), movieJsonObject.getInt("numReviews"))
                .build();
    }
}
