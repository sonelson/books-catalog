package dev.sol.catalog.jaxresources;

import dev.sol.catalog.dao.AuthorDAO;
import dev.sol.catalog.entities.Author;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

/**
 * Jersey resource class is associated with a author URI
 *
 * @author solo
 */
@Path("/author")
@Produces(MediaType.APPLICATION_JSON)
public class AuthorResource {

    // DAO object to manipulate authors
    private AuthorDAO authorDAO;

    /**
     * Constructor
     *
     * @param authorDAO DAO object to manipulate authors.
     */
    public AuthorResource(AuthorDAO authorDAO) {
        this.authorDAO = authorDAO;
    }


    /**
     * Method looks for an author by id.
     * <p>
     * The transaction boundary scope 'UnitOfWork' will automatically open a
     * session, begin a transaction, call findById, commit the transaction, and
     * finally close the session. If an exception is thrown, the transaction
     * is rolled back.
     *
     * @param id the id of an author
     * @return Optional containing the found author or an empty Optional
     */
    @GET
    @Path("/{id}")
    @UnitOfWork
    public Optional<Author> findById(@PathParam("id") LongParam id) {
        return authorDAO.findById(id.get());
    }

    @POST
    @UnitOfWork
    public Author createAuthor(Author author) {
        return authorDAO.create(author);
    }

    @GET
    @UnitOfWork
    public List<Author> listAuthors() {
        return authorDAO.findAll();
    }
}
