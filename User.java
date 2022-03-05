import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class User {
    interface Transaction{
        void updateRentedBook(Record record, String title, LocalDateTime initialDate, Integer rentedDays);
    }
    interface Sort{
        List<Book> sortRating(List<Book> books);
    }
    interface Available{
        List<Book> check(List<Book> books);
    }

    public static void main(String[] args) {

        Available availableBooks = (books) -> {
            return books.stream()
                    .filter(Book::getAvailable)
                    .collect(Collectors.toList());
        };

        Transaction rental = (record, title, initialDate, rentedDays) -> {
            List<Book> books = record.getBooks();
            availableBooks.check(books).forEach(b -> {
                b.setAvailable(false);
            });
            record.setRecord(books, title, initialDate, rentedDays);
        };

        List<Book> listBooks = new ArrayList<>();
        Book bookA = new Book("A", 4.5, true);
        Book bookB = new Book("B", 4.0, false);
        Book bookC = new Book("C", 4.3, true);
        Book bookD = new Book("D", 3.3, true);
        Book bookE = new Book("E", 3.0, true);
        Book bookF = new Book("F", 4.1, true);
        Book bookG = new Book("G", 3.2, false);
        Book bookH = new Book("H", 4.8, false);
        Book bookI = new Book("I", 3.4, true);

        listBooks.add(bookA);
        listBooks.add(bookB);
        listBooks.add(bookC);
        listBooks.add(bookD);
        listBooks.add(bookE);
        listBooks.add(bookF);
        listBooks.add(bookG);
        listBooks.add(bookH);
        listBooks.add(bookI);

        System.out.println("List of Books:");
        printListOfBooks(listBooks);

        System.out.println("\nList of available Books:");
        printListOfBooks(availableBooks.check(listBooks));

////        Rent Books
        List<Book> rentedBookList = new ArrayList<>();
        rentedBookList.add(bookC);
        rentedBookList.add(bookG);

        Record rentedBooksRecord = new Record(rentedBookList);

        int days = 3;
        LocalDateTime initDate = LocalDateTime.now().minus(Period.ofDays(days));
        initDate = initDate.minus(Duration.ofHours(5));

        rental.updateRentedBook(rentedBooksRecord, "C", LocalDateTime.now(), 5);
        rental.updateRentedBook(rentedBooksRecord, "G", initDate, days);

        System.out.println("\nRecord of Rented Books:");
        printRecord(rentedBooksRecord);

        System.out.println("\nList of available Books:");
        printListOfBooks(availableBooks.check(listBooks));

////        Books with Rating > 4
        System.out.println("\nList of Books with rating >4:");
        List<Book> goodRating = listBooks.stream()
                .filter(b -> b.getRating()>4.0)
                .collect(Collectors.toList());
        printListOfBooks(goodRating);

        System.out.println("\nList of Rating >4:");
        List<Double> ratingsMoreThanFour = goodRating.stream()
                .map(b -> b.getRating())
                .collect(Collectors.toList());
        System.out.println(ratingsMoreThanFour);

//        Highest Rated Books
        System.out.println("\nBiggest rating:");
        System.out.println(ratingsMoreThanFour.stream().reduce(0.0, Double::max));

        System.out.println("\nList of Books:");
        printListOfBooks(listBooks);
    }

    static void printListOfBooks(List<Book> books){
        System.out.println("Title \t Rating \t Available \t\t Initial Rented Date \t\t\tFinal Rented Date \t\t Rented Period (Days) \t Exceeded Duration (s)");
        books.forEach(System.out::println);
    }
    static void printRecord(Record records){
        List<Book> books =  records.getBooks();
        printListOfBooks(books);
    }
}
