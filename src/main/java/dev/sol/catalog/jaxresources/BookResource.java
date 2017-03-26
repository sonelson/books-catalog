package dev.sol.catalog.jaxresources;

import dev.sol.catalog.dao.BookDAO;
import dev.sol.catalog.entities.Author;
import dev.sol.catalog.entities.Book;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * API resource representation for Book
 *
 * @author solo
 */
@Path("/book")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

    private BookDAO bookDAO;

    /**
     * Constructor
     *
     * @param bookDAO DAO object to work with books
     */
    public BookResource(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @POST
    @UnitOfWork
    public Book createBook(Book book) {

        Set<Author> authorSet = book.getAuthors();

        for (Author author: authorSet) {
            //System.out.println("Adding AUTHOR TO BOOK =>"+ author.getName());
            author.setBook(book);
        }

        return bookDAO.create(book);
    }

    /**
     * List books whose title contains the passed parameter as a substring.
     * Returns all books stored in the catalog database if no argument is
     * passed.
     *
     * @param title query parameter
     * @return list of books
     *
     */
    @GET
    @UnitOfWork
    public List<Book> listBooks(@QueryParam("title") Optional<String> title) {
        if (title.isPresent()) {
            return bookDAO.findByTitle(title.get());
        }
        return bookDAO.findAll();
    }

    /**
     * Method fetches book by id
     *
     * @param id the id of the book passed as a path parameter
     * @return Optional containing the found book or an empty Optional
     * otherwise.
     */
    @GET
    @Path("/{id}")
    @UnitOfWork
    public Optional<Book> findById(@PathParam("id") LongParam id) {
        return bookDAO.findById(id.get());
    }

}
