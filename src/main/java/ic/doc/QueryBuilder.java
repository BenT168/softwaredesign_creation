package ic.doc;

public class QueryBuilder {

    private String title;
    private String firstname;
    private String lastname;
    private Integer yearBefore;
    private Integer yearAfter;

    private QueryBuilder(){}

    public static QueryBuilder query() {
        return new QueryBuilder();
    }

    public BookSearchQuery build(){
        return new BookSearchQuery(firstname, lastname, title,
                yearBefore, yearAfter);
    }

    public QueryBuilder withMatchesAuthorFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public QueryBuilder withMatchesAuthorSurname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public QueryBuilder withPublishedBefore(Integer year) {
        this.yearBefore = year;
        return this;
    }

    public QueryBuilder withPublishedSince(Integer year) {
        this.yearAfter = year;
        return this;
    }

    public QueryBuilder withMatchesTitle(String title) {
        this.title = title;
        return this;
    }

}
