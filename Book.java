import java.time.LocalDateTime;
import java.util.Optional;

public class Book {
    private String title;
    private Double rating;
    private Boolean available;
    private Optional<Integer> publishedYear;

    private LocalDateTime initialDate = null;
    private LocalDateTime finalDate = null;
    private Integer rentedDays = 0;
    private Long exceededDuration = 0L;

    public Book(String title, Double rating, Boolean available) {
        this.title = title;
        this.rating = rating;
        this.available = available;
        this.publishedYear = Optional.empty();
    }

    public Book(String title, Double rating, Boolean available, Optional<Integer> publishedYear) {
        this.title = title;
        this.rating = rating;
        this.available = available;
        this.publishedYear = publishedYear;
    }

    public Integer getPublishedYear() {
        if (publishedYear.isPresent()) {
            return publishedYear.get();
        } else {
            return 0;
        }
    }

    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = Optional.of(publishedYear);
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

    @Override
    public String toString() {
        Integer yearInt = getPublishedYear();
        String yearStr = String.valueOf(yearInt);
        if (yearInt == 0) yearStr = "";

        String s =  String.format("%-13s%-18s%-13.1f%-13b", title, yearStr, rating, available);

        return s;
    }

}

