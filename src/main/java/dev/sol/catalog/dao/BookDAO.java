package dev.sol.catalog.dao;

import dev.sol.catalog.entities.Author;
import dev.sol.catalog.entities.Book;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

/**
 * @author solo
 */
public class BookDAO extends AbstractDAO<Book> {

    public BookDAO(SessionFactory factory) {
        super(factory);
    }

    /**
     * Method looks for an author by id.
     *
     * @param id the id of an book we are looking for.
     * @return Optional containing the book or an empty Optional
     *
     */
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(get(id));
    }

    public Book create(Book book) {
        return persist(book);
    }

    /**
     * Method returns all books stored in the database.
     *
     * @return list of all books
     */
    public List<Book> findAll() {
        return list(namedQuery("dev.sol.catalog.entities.Book.findAll"));
    }
}
