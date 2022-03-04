import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Record {
    private List<Book> books;
    private LocalDateTime date;

    public Record() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("-",0.0, false));
        this.books = books;
        this.date = LocalDateTime.now();
    }

    public Record(List<Book> books, LocalDateTime date) {
        this.books = books;
        this.date = date;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
