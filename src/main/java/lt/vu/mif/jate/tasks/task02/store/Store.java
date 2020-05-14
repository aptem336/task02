package lt.vu.mif.jate.tasks.task02.store;

import lombok.Getter;
import lt.vu.mif.jate.tasks.task02.search.SearchManager;
import lt.vu.mif.jate.tasks.task02.store.model.Item;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashSet;

/**
 * Top level domain store class.
 *
 * @author valdo
 */
@Getter
public class Store extends SearchManager<Item> {

    /**
     * List of fields used in store
     */
    public static final String TITLE_FIELD = "TITLE";
    public static final String SUBTITLE_FIELD = "SUBTITLE";
    public static final String DESCRIPTION_FIELD = "DESCRIPTION";
    public static final String CATEGORY_FIELD = "CATEGORY";
    public static final String AUTHOR_FIELD = "AUTHOR";
    public static final String PUBLISHER_FIELD = "PUBLISHER";
    public final Collection<Item> items = new HashSet<>();

    /**
     * Load objects from the input stream.
     *
     * @param is input stream.
     */
    public final void load(final InputStream is) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                stringBuilder.append(str);
            }
        } catch (IOException ignored) {
        }
        new JSONArray(stringBuilder.toString()).forEach(jsonObject ->
                getSearchItems().add(ItemFactory.createItem((JSONObject) jsonObject)));
    }

    @Override
    protected Collection<Item> getSearchItems() {
        return items;
    }

    public Collection<Item> getItems() {
        return getSearchItems();
    }
}
