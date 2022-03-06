import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Record {
    private Book book;
    private User user;
    private String id;
    private LocalDateTime initialDate = LocalDateTime.now();
    private LocalDateTime finalDate;
    private Integer rentedDays;
    private Long exceededDuration = 0L;

    public Record(Book book, User user, Integer days) {
        this.book = book;
        this.user = user;
        this.rentedDays = days;
        this.finalDate = initialDate.plus(Period.ofDays(days));
        setExceededDuration(calcExceededDuration(this.finalDate));
    }

    private Long calcExceededDuration(LocalDateTime finalDate){
        Long exceededDuration = Duration.between(finalDate, LocalDateTime.now()).getSeconds();
        if (exceededDuration < 0) exceededDuration = 0L;
        return exceededDuration;
    }

    public Book getBook() {
        return book;
    }

    public void setBooks(Book book) {
        this.book = book;
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

    public LocalDateTime getInitialDate() { return initialDate; }

    public void setInitialDate(LocalDateTime initialDate) { this.initialDate = initialDate; }

    public LocalDateTime getFinalDate() { return finalDate; }

    public void setFinalDate(LocalDateTime finalDate) { this.finalDate = finalDate; }

    public Integer getRentedDays() { return rentedDays; }

    public void setRentedDays(Integer rentedDays) { this.rentedDays = rentedDays; }

    public Long getExceededDuration() { return exceededDuration; }

    public void setExceededDuration(Long exceededDuration) { this.exceededDuration = exceededDuration; }

}