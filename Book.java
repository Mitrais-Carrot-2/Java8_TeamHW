import java.time.LocalDateTime;

public class Book {
    private String title;
    private Double rating;
    private Boolean available;

    private LocalDateTime initialDate;
    private LocalDateTime finalDate;
    private Integer rentedDays = 0;
    private Long exceededDuration = Long.valueOf(0);

    public Book(String title, Double rating, Boolean available) {
        this.title = title;
        this.rating = rating;
        this.available = available;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public LocalDateTime getInitialDate() { return initialDate; }

    public void setInitialDate(LocalDateTime initialDate) { this.initialDate = initialDate; }

    public LocalDateTime getFinalDate() { return finalDate; }

    public void setFinalDate(LocalDateTime finalDate) { this.finalDate = finalDate; }

    public Integer getRentedDays() { return rentedDays; }

    public void setRentedDays(Integer rentedDays) { this.rentedDays = rentedDays; }

    public Long getExceededDuration() { return exceededDuration; }

    public void setExceededDuration(Long exceededDuration) { this.exceededDuration = exceededDuration; }

    @Override
    public String toString() {
        String s =  String.format("%-10s%-13.1f%-13b", title, rating, available);
        if (rentedDays > 0) s = String.format("%-10s%-13.1f%-12b%-30s%-40s%-20d%-30d", title, rating, available, initialDate, finalDate, rentedDays, exceededDuration);

        return s;
    }

}

