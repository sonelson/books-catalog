package dev.sol.catalog.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * JPA entity class to represent Book object in the database.
 *
 * @author solo
 */

@Entity
@Table(name = "book")
@NamedQueries(
        {
                @NamedQuery(
                        name = "dev.sol.catalog.entities.Book.findAll",
                        query = "SELECT b FROM Book b"
                ),
                @NamedQuery(
                        name = "dev.sol.catalog.entities.Book.findByTitle",
                        query = "SELECT b FROM Book b " +
                                "WHERE b.title LIKE :title"
                ),
                @NamedQuery(
                        name = "dev.sol.catalog.entities.Book.findBooksByAuthor",
                        query = "SELECT b FROM Book b JOIN b.authors a " +
                                "WHERE a.fullName LIKE :author"
                )
        }
)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "isbn", nullable = false)
    private String isbn;

    @Column(name = "title", nullable = false)
    private String title;

    //@JsonBackReference
    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="book")
    private Set<Author> authors;

    public Book() {
    }

    public Book(String isbn, String title) {
        setIsbn(isbn);
        setTitle(title);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Book)) {
            return false;
        }

        final Book that = (Book) o;

        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.isbn, that.isbn) &&
                Objects.equals(this.title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn, title);
    }


}
