package dev.sol.catalog.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import dev.sol.catalog.entities.Author;
import org.hibernate.Session;
import org.hibernate.context.internal.ManagedSessionContext;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.dropwizard.testing.junit.DAOTestRule;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author solo
 */
class AuthorDAOTest {

    @Rule
    public DAOTestRule daoTestRule = DAOTestRule.newBuilder()
            .addEntityClass(Author.class)
            .build();

    private AuthorDAO authorDAO;
    private Session session;

    @BeforeEach
    void setUp() {
        authorDAO = new AuthorDAO(daoTestRule.getSessionFactory());

        session = daoTestRule.getSessionFactory().openSession();
        ManagedSessionContext.bind(session);
    }

    @AfterEach
    void tearDown() {
        session.close();
    }

    @Test
    void findById() {
        final Author tom = daoTestRule.inTransaction(
                () -> authorDAO.create(new Author("Tom")));
        assertEquals(authorDAO.findById(tom.getId()), Optional.of(tom));
    }

    @Test
    void createAuthor() {

        final Author tom = daoTestRule.inTransaction(
                () -> authorDAO.create(new Author("Tom")));

        assertNotNull (tom.getId());
        assertEquals("Tom", tom.getName());
    }

    @Test
    void findAll() {
        daoTestRule.inTransaction(() -> {
            authorDAO.create(new Author("TomJerry"));
        });

        daoTestRule.inTransaction(() -> {
            authorDAO.create(new Author("JimBerry"));
        });

        final List<Author> authorList = authorDAO.findAll();
        assertThat(authorList).extracting("name").containsOnly("TomJerry", "JimBerry");
        //assertThat(authorList).extracting("name").containsOnly("TomJerry");

    }

}