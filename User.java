import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class User {
    interface Transaction {
        Record rentBooks(List<Book> books, LocalDateTime date);
    }

    interface Sort {
        List<Book> sortRating(List<Book> books);
    }

    interface Available {
        List<Book> check(List<Book> books);
    }

    public static void main(String[] args) {
        Record record = new Record();
        Available availableBooks = (books) -> {
            return books.stream().filter(b -> b.getAvailable() == true).collect(Collectors.toList());
        };
        Transaction rentA = (books, date) -> {
            availableBooks.check(books).forEach(b -> {
                b.setAvailable(false);
            });
            return new Record(books, date);
        };

        List<Book> listBooks = new ArrayList<>();
        Book bookA = new Book("A", 4.5, true);
        Book bookB = new Book("B", 4.0, false);
        Book bookC = new Book("C", 4.3, true);
        Book bookD = new Book("D", 3.3, true);
        Book bookE = new Book("E", 3.0, true);
        Book bookF = new Book("F", 4.1, true);
        Book bookG = new Book("G", 3.2, true);
        Book bookH = new Book("H", 4.8, false);
        Book bookI = new Book("1", 3.4, true);

        // add published year of the book if known

        bookA.setPublishedYear(2000);
        bookB.setPublishedYear(1971);
        bookC.setPublishedYear(2012);
        bookF.setPublishedYear(2019);
        bookH.setPublishedYear(2019);
        bookI.setPublishedYear(2006);

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

        System.out.println("List of available Books:");
        printListOfBooks(availableBooks.check(listBooks));

        List<Book> recordTransaction = new ArrayList<>();
        recordTransaction.add(bookC);
        recordTransaction.add(bookG);
        System.out.println("List of rented Books:");
        printListOfBooks(recordTransaction);

        record = rentA.rentBooks(recordTransaction, LocalDateTime.now());

        System.out.println("List of available Books:");
        printListOfBooks(availableBooks.check(listBooks));

        System.out.println("List of Books with rating >4:");
        List<Book> goodRating = listBooks.stream()
                .filter(b -> b.getRating() > 4.0)
                .collect(Collectors.toList());
        printListOfBooks(goodRating);

        System.out.println("List of Rating >4:");
        List<Double> ratingsMoreThanFour = goodRating.stream()
                .map(b -> b.getRating())
                .collect(Collectors.toList());
        System.out.println(ratingsMoreThanFour);

        System.out.println("Biggest rating:");
        System.out.println(ratingsMoreThanFour.stream().reduce(0.0, Double::max));

        System.out.println("Record:");
        printRecord(record);

        printListOfBooksByYearPublished(listBooks, 2019);
        ;
    }

    static void printListOfBooksByYearPublished(List<Book> books, int year) {
        List<Book> onYear = books.stream()
                .filter(book -> book.getPublishedYear() == year)
                .collect(Collectors.toList());
        System.out.println("Book that published on " + String.valueOf(year) + " is:");
        for (Book book : onYear) {
            System.out.println(book.getTitle());
        }
    }

    static void printListOfBooks(List<Book> books) {
        books.forEach(a -> {
            System.out.println("------------------------");
            System.out.println(a.getTitle());
            System.out.println(a.getRating());
            System.out.println(a.getAvailable());
        });
    }

    static void printRecord(Record records) {
        List<Book> books = records.getBooks();
        LocalDateTime rentDate = records.getDate();
        printListOfBooks(books);
        System.out.println("------------------------");
        System.out.println(rentDate);
    }
}
