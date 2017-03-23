package dev.sol.catalog.jaxresources;

import dev.sol.catalog.dao.BookDAO;
import dev.sol.catalog.entities.Author;
import dev.sol.catalog.entities.Book;
import dev.sol.catalog.jsonrep.BookPayload;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
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
    //public Book createBook(BookPayload bookJson) {
    public Book createBook(Book book) {

       /* Book bookObj = new Book();
        bookObj.setTitle(bookJson.getTitle());
        bookObj.setIsbn(bookJson.getIsbn());

        Set<Author> authorSet = new HashSet<>(bookJson.getAuthors());
        bookObj.setAuthors(authorSet);*/

        return bookDAO.create(book);
    }

    @GET
    @UnitOfWork
    public List<Book> listBooks() {
        return bookDAO.findAll();
    }
}
