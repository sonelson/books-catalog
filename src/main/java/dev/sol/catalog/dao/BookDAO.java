package dev.sol.catalog.dao;

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
     * Method looks for a book by id.
     *
     * @param id the id of an book searched for
     * @return Optional containing the book or an empty Optional
     *
     */
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(get(id));
    }

    /**
     * Method returns all books stored in the database.
     *
     * @return list of all books
     */
    public List<Book> findAll() {

        return list(namedQuery("dev.sol.catalog.entities.Book.findAll"));
    }

    /**
     * Method returns all books whose title contains the passed
     * parameter as a substring.
     *
     * @param title query parameter
     * @return list of all books filtered by matching title
     */
    public List<Book> findByTitle(String title) {

        StringBuilder builder = new StringBuilder("%");
        builder.append(title).append("%");
        return list(
                namedQuery("dev.sol.catalog.entities.Book.findByTitle")
                        .setParameter("title", builder.toString())
        );
    }

    /**
     * Method creates a new book record
     *
     * @return Book
     */
    public Book create(Book book) {
        return persist(book);
    }

    /**
     * Method updates existing book record
     *
     */
    public void update(Book book) {
        currentSession().saveOrUpdate(book);
    }


    /**
     * Method deletes existing book record
     *
     */
    public void delete(Book book) {
        currentSession().delete(book);
    }

}
