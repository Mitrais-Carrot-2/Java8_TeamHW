import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Record {
    private String title;
    private List<Book> books;
    private LocalDateTime initialDate;
    private LocalDateTime finalDate;
    private Long exceededDuration = 0L;

    public Record() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("-",0.0, false));
        this.books = books;
        this.initialDate = LocalDateTime.now();
    }

    public Record(List<Book> books){
        this.books = books;
    }

    public void setRecord(List<Book> books, String title, LocalDateTime initialDate, Integer days) {
        this.books = books;
        this.initialDate = initialDate;
        this.finalDate = initialDate.plus(Period.ofDays(days));

        calcExceededDuration(finalDate);

        this.books.forEach(b -> {
            if (b.getTitle() == title){
                b.setInitialDate(initialDate);
                b.setFinalDate(this.finalDate);
                b.setRentedDays(days);
                b.setExceededDuration(this.exceededDuration);
            }
        });
    }

    private void calcExceededDuration(LocalDateTime finalDate){
        this.exceededDuration = Duration.between(finalDate, LocalDateTime.now()).getSeconds();
        if (this.exceededDuration < 0) this.exceededDuration = 0L;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}
