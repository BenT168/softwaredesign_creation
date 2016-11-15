package ic.doc;

public class BookBuilder {

    private String title;
    private String firstname;
    private String lastname;
    private Integer dateBefore;
    private Integer dateAfter;

    private BookBuilder(){}

    public static BookBuilder bookStr() {
        return new BookBuilder();
    }

    public BookSearchQuery build(){
       
        BookSearchQuery book = new BookSearchQuery(firstname, lastname, title, dateBefore, dateAfter);
        book.execute();
        return book;
    }

    public BookBuilder withMatchesAuthorFirstName(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public BookBuilder withMatchesAuthorLastName(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public BookBuilder withPublishedBefore(Integer year) {
        this.dateBefore = year;
        return this;
    }

    public BookBuilder withPublishedSince(Integer year) {
        this.dateAfter = year;
        return this;
    }

    public BookBuilder withMatchesTitle(String title) {
        this.title = title;
        return this;
    }

}
