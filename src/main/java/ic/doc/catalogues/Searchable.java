package ic.doc.catalogues;

import ic.doc.Book;

import java.util.List;

public interface Searchable {

    List<Book> searchFor(String author);
}
