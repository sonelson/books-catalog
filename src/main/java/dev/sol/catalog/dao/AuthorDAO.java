package dev.sol.catalog.dao;

import dev.sol.catalog.entities.Author;
import dev.sol.catalog.entities.Book;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

/**
 *
 *
 * @author solo
 */
public class AuthorDAO extends AbstractDAO<Author> {

    public AuthorDAO(SessionFactory factory) {
        super(factory);
    }

    /**
     * Method looks for an author by id.
     *
     * @param id the id of an employee we are looking for.
     * @return Optional containing the found employee or an empty Optional
     *
     */
    public Optional<Author> findById(long id) {
        return Optional.ofNullable(get(id));
    }



    /**
     * Method returns all authors stored in the database.
     *
     * @return list of all authors
     */
    public List<Author> findAll() {
        return list(namedQuery("dev.sol.catalog.entities.Author.findAll"));
    }

    public Author create(Author author) {
        return persist(author);
    }

    public void update(Author author) {
        currentSession().saveOrUpdate(author);
    }

    public void delete(Author author) {
        currentSession().delete(author);
    }
}
