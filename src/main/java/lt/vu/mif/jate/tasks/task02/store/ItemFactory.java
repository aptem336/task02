/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.vu.mif.jate.tasks.task02.store;

import lt.vu.mif.jate.tasks.task02.store.model.Book;
import lt.vu.mif.jate.tasks.task02.store.model.Item;
import lt.vu.mif.jate.tasks.task02.store.model.Movie;
import lt.vu.mif.jate.tasks.task02.store.model.WrongItemFormatException;
import org.json.JSONArray;
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
                bookJsonObject.has("title") ? bookJsonObject.getString("title") : null,
                bookJsonObject.has("description") ? bookJsonObject.getString("description") : null)
                .categories(bookJsonObject.has("categories") ? bookJsonObject.getJSONArray("categories") : new JSONArray("[\"Book\"]"))
                .rating(bookJsonObject.has("averageRating") ? bookJsonObject.getDouble("averageRating") : 0,
                        bookJsonObject.has("ratingsCount") ? bookJsonObject.getInt("ratingsCount") : 0)
                .subtitle(bookJsonObject.has("subtitle") ? bookJsonObject.getString("subtitle") : null)
                .publisher(bookJsonObject.has("publisher") ? bookJsonObject.getString("publisher") : null)
                .pages(bookJsonObject.has("pageCount") ? bookJsonObject.getInt("pageCount") : 0)
                .authors(bookJsonObject.has("authors") ? bookJsonObject.getJSONArray("authors") : new JSONArray())
                .build();
    }

    public static Movie createMovie(JSONObject movieJsonObject) {
        if (!movieJsonObject.has("id")) {
            throw new WrongItemFormatException();
        }
        return new Movie.Builder(
                movieJsonObject.getBigInteger("id"),
                movieJsonObject.has("name") ? movieJsonObject.getString("name") : null,
                movieJsonObject.has("longDescription") ? movieJsonObject.getString("longDescription") : null)
                .categories(movieJsonObject.has("categoryPath") ? movieJsonObject.getString("categoryPath").replace("Movies/", "") : "/")
                .rating(movieJsonObject.has("customerRating") ? movieJsonObject.getDouble("customerRating") : 0,
                        movieJsonObject.has("numReviews") ? movieJsonObject.getInt("numReviews") : 0)
                .build();
    }

    public static Item createItem(JSONObject jsonObject) {
        return jsonObject.has("isbn") ? createBook(jsonObject) : createMovie(jsonObject);
    }
}
