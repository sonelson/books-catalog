package dev.sol.catalog.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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
                        name = "dev.sol.catalog.entity.Book.findAll",
                        query = "SELECT b FROM Book b"
                )
        }
)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "isbn", nullable = false)
    private String isbn;

    @Column(name = "title", nullable = false)
    private String title;

    private Author author;

    @ManyToOne
    private List<Author> authors;

    public Book() {
    }

    public Book(String isbn, String title, Author author) {
        setIsbn(isbn);
        setTitle(title);
        setAuthor(author);
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

    public String getAuthor() {
        return title;
    }

    public void setAuthor(Author author) {
        this.author = author;
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
