package ic.doc.catalogues;

import ic.doc.Book;

import java.util.List;

public interface Seachable {

    List<Book> searchFor(String author);
}
