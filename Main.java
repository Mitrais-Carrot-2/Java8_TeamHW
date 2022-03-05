import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    interface Transaction {
        void rentBooks(List<Book> books, LocalDateTime date, User user, String id);
    }

    interface Sort {
        List<Book> sortRating(List<Book> books);
    }

    interface Available {
        List<Book> check(List<Book> books);
    }

    public static void main(String[] args) throws InterruptedException {
        List<Record> records = new ArrayList<>();
        Available availableBooks = (books) -> {
            return books.stream().filter(b -> b.getAvailable() == true).collect(Collectors.toList());
        };
        Available unavailableBooks = (books) -> {
            return books.stream().filter(b -> b.getAvailable() == false).collect(Collectors.toList());
        };
        Transaction rent = (books, date, user, id) -> {
            books.forEach(b -> {
                if (b.getAvailable()){
                    b.setAvailable(false);
                    records.add(new Record(b, date, user, id));
                }
//                else {
//                    System.out.println("------------------------");
//                    System.out.println(b.getTitle()+" unavailaible");
//                }
            });
        };

        List<Book> listBooks = new ArrayList<>();
        Book bookA = new Book("Book A", 4.5, true);
        Book bookB = new Book("Book B", 4.0, false);
        Book bookC = new Book("Book C", 4.3, true);
        Book bookD = new Book("Book D", 3.3, true);
        Book bookE = new Book("Book E", 3.0, true);
        Book bookF = new Book("Book F", 4.1, true);
        Book bookG = new Book("Book G", 3.2, true);
        Book bookH = new Book("Book H", 4.8, false);
        Book bookI = new Book("Book I", 3.4, true);

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

        System.out.println("List of Books: "+ listBooks.size());
        printListOfBooks(listBooks);

        System.out.println("List of available Books: "+ availableBooks.check(listBooks).size());
        printListOfBooks(availableBooks.check(listBooks));

        List<Book> recordTransactionA = new ArrayList<>();
        recordTransactionA.add(bookC);
        recordTransactionA.add(bookG);

        List<Book> recordTransactionB = new ArrayList<>();
        recordTransactionB.add(bookA);
        recordTransactionB.add(bookH);

        System.out.println("List of unavailable Books: "+ unavailableBooks.check(listBooks).size());
        printListOfBooks(unavailableBooks.check(listBooks));

        User userA = new User(1, "User A", "0838423658");
        User userB = new User(2, "User B", "0838254567");

        rent.rentBooks(recordTransactionA, LocalDateTime.now(), userA, LocalTime.now().toString());
        Thread.sleep(1000);
        rent.rentBooks(recordTransactionB, LocalDateTime.now(), userB, LocalTime.now().toString());

        System.out.println("List of available Books: "+ availableBooks.check(listBooks).size());
        printListOfBooks(availableBooks.check(listBooks));

        System.out.println("List of Books with rating >4:");
        List<Book> goodRating = listBooks.stream()
                .filter(b -> b.getRating() > 4.0)
                .collect(Collectors.toList());
        printListOfBooks(goodRating);

        System.out.println("------------------------");
        System.out.println("List of Book with rating >4:");
        List<Double> ratingsMoreThanFour = goodRating.stream()
                .map(b -> b.getRating())
                .collect(Collectors.toList());
        System.out.println(ratingsMoreThanFour);

        System.out.println("------------------------");
        System.out.println("Biggest rating:");
        System.out.println(ratingsMoreThanFour.stream().reduce(0.0, Double::max));

        System.out.println("------------------------");
        System.out.println("Record:");
        printRecord(records);

        printListOfBooksByYearPublished(listBooks, 2019);
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

    static void printRecord(List<Record> records) {
        records.forEach(r -> {
            LocalDateTime rentDate = r.getDate();
            System.out.println(r.getUser().getName());
            System.out.println(r.getBooks().getTitle());
            System.out.println(rentDate.getDayOfMonth()+" "+rentDate.getMonth()+" "+rentDate.getYear()+" ~ "+rentDate.getHour()+":"+rentDate.getMinute()+":"+rentDate.getSecond());
            System.out.println("------------------------");
        });
    }
}
