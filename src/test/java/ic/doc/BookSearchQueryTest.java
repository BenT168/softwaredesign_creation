package ic.doc;

import ic.doc.catalogues.BritishLibraryCatalogue;
import ic.doc.catalogues.Searchable;
import org.junit.Test;

import java.util.List;

import static ic.doc.BookBuilder.bookStr;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class BookSearchQueryTest {

    Searchable searchable = BritishLibraryCatalogue.getInstance();

    @Test
    public void searchesForBooksInLibraryCatalogueByAuthorSurname() {

        List<Book> books = bookStr().withMatchesAuthorLastName("dickens").build().execute(searchable);

        assertThat(books.size(), is(2));
        assertTrue(books.get(0).matchesAuthor("dickens"));
    }


    @Test
    public void searchesForBooksInLibraryCatalogueByAuthorFirstname() {

        List<Book> books = bookStr().withMatchesAuthorFirstName("Jane").build().execute(searchable);

        assertThat(books.size(), is(2));
        assertTrue(books.get(0).matchesAuthor("Austen"));
    }

    @Test
    public void searchesForBooksInLibraryCatalogueByTitle() {

        List<Book> books = bookStr().withMatchesTitle("Two Cities").build().execute(searchable);

        assertThat(books.size(), is(1));
        assertTrue(books.get(0).matchesAuthor("dickens"));
    }

    @Test
    public void searchesForBooksInLibraryCatalogueBeforeGivenPublicationYear() {

        List<Book> books = bookStr().withPublishedSince(1700).build().execute(searchable);

        assertThat(books.size(), is(1));
        assertTrue(books.get(0).matchesAuthor("Shakespeare"));
    }

    @Test
    public void searchesForBooksInLibraryCatalogueAfterGivenPublicationYear() {

        List<Book> books = bookStr().withPublishedBefore(1950).build().execute(searchable);

        assertThat(books.size(), is(1));
        assertTrue(books.get(0).matchesAuthor("Golding"));
    }

    @Test
    public void searchesForBooksInLibraryCatalogueWithCombinationOfParameters() {

        List<Book> books = bookStr().withMatchesAuthorLastName("dickens").
                withPublishedSince(1840).build().execute(searchable);

        assertThat(books.size(), is(1));
        assertTrue(books.get(0).matchesAuthor("charles dickens"));
    }
}
