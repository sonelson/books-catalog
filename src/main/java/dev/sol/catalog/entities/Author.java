package dev.sol.catalog.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @Column(name = "full_name", nullable = false)
    private String fullName;

    //@JsonIgnore
    //@JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="book_id", nullable=false)
    private Book book;


    public Author() {
    }

    public Author(String name) {
        setFullName(name);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String name) {
        this.fullName = name;
    }


    @JsonIgnore
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
                Objects.equals(this.fullName, that.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName);
    }

}
