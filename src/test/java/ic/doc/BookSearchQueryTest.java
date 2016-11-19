package ic.doc;

import ic.doc.catalogues.BritishLibraryCatalogue;
import ic.doc.catalogues.Catalogue;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static ic.doc.QueryBuilder.query;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class BookSearchQueryTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();
    final Catalogue fake = context.mock(Catalogue.class);

    Catalogue catalogue = BritishLibraryCatalogue.getInstance();

    @Test
    public void searchesForBooksInLibraryCatalogueByAuthorSurname() {

        List<Book> books = query().withMatchesAuthorSurname("dickens").
                build().execute(catalogue);

        searchForBook(books);
        assertThat(books.size(), is(2));
        assertTrue(books.get(0).matchesAuthor("dickens"));
        context.assertIsSatisfied();
    }


    @Test
    public void searchesForBooksInLibraryCatalogueByAuthorFirstname() {

        List<Book> books = query().withMatchesAuthorFirstname("Jane").
                build().execute(catalogue);

        searchForBook(books);
        assertThat(books.size(), is(2));
        assertTrue(books.get(0).matchesAuthor("Austen"));
        context.assertIsSatisfied();
    }

    @Test
    public void searchesForBooksInLibraryCatalogueByTitle() {

        List<Book> books = query().withMatchesTitle("Two Cities").
                build().execute(catalogue);

        searchForBook(books);
        assertThat(books.size(), is(1));
        assertTrue(books.get(0).matchesAuthor("dickens"));
        context.assertIsSatisfied();
    }

    @Test
    public void searchesForBooksInLibraryCatalogueBeforeGivenPublicationYear() {

        List<Book> books = query().withPublishedSince(1700).
                build().execute(catalogue);

        searchForBook(books);
        assertThat(books.size(), is(1));
        assertTrue(books.get(0).matchesAuthor("Shakespeare"));
        context.assertIsSatisfied();
    }

    @Test
    public void searchesForBooksInLibraryCatalogueAfterGivenPublicationYear() {

        List<Book> books = query().withPublishedBefore(1950).
                build().execute(catalogue);

        searchForBook(books);
        assertThat(books.size(), is(1));
        assertTrue(books.get(0).matchesAuthor("Golding"));
        context.assertIsSatisfied();
    }

    @Test
    public void searchesForBooksInLibraryCatalogueWithCombinationOfParameters() {

        List<Book> books = query().withMatchesAuthorSurname("dickens").
                withPublishedSince(1840).build().execute(catalogue);

        searchForBook(books);
        assertThat(books.size(), is(1));
        assertTrue(books.get(0).matchesAuthor("charles dickens"));
        context.assertIsSatisfied();
    }

    private void searchForBook(List<Book> books){
        context.checking(new Expectations() {{
            never(fake).searchFor(books.toString()); will(returnValue(false));
        }});
    }
}
