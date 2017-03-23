package dev.sol.catalog.entities;

import javax.persistence.*;
import java.util.Objects;

/**
 * JPA entity class to represent Author object in the database.
 *
 * @author solo
 */

@Entity
@Table(name = "author")
@NamedQueries(
        {
                @NamedQuery(
                        name = "dev.sol.catalog.entities.Author.findAll",
                        query = "SELECT a FROM Author a"
                )
        }
)
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="book_id", nullable=false)
    private Book book;

    public Author() {
    }

    public Author(String name) {
        setName(name);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Book getBook()
    {
        return book;
    }

    public void setBook(Book book)
    {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Author)) {
            return false;
        }

        final Author that = (Author) o;

        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

}
