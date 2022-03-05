import java.util.Optional;

public class Book {
    private String title;
    private Double rating;
    private Boolean available;
    private Optional<Integer> publishedYear;


    public Book(String title, Double rating, Boolean available) {
        this.title = title;
        this.rating = rating;
        this.available = available;
        this.publishedYear = Optional.empty();
    }

    public Integer getPublishedYear() {
        if (publishedYear.isPresent()) {
            return publishedYear.get();
        } else{
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
}
