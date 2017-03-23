package dev.sol.catalog.jsonrep;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.sol.catalog.entities.Author;

import java.util.List;

/**
 *
 *
 * @author solo on 3/22/2017.
 */
public class BookPayload {

    private String isbn;
    private String title;

    private List<Author> authors;

    @JsonProperty
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @JsonProperty
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty
    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
