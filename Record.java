import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Record {
    private Book books;
    private LocalDateTime date;
    private User user;
    private String id;

    public Record(Book books, LocalDateTime date, User user, String id) {
        this.books = books;
        this.date = date;
        this.user = user;
        this.id = id;
    }

    public Book getBooks() {
        return books;
    }

    public void setBooks(Book books) {
        this.books = books;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
