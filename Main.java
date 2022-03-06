import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    interface Transaction{
        void rentedBook(List<Book> books, User user, Integer days);
    }

    interface Sort {
        List<Book> sortRating(List<Book> books);
    }

    interface Available {
        List<Book> check(List<Book> books);
    }

    public static void main(String[] args) {
        List<Record> records = new ArrayList<>();
        Available availableBooks = (books) -> {
            return books.stream()
                    .filter(Book::getAvailable)
                    .collect(Collectors.toList());
        };

        Transaction rental = (books, user, days) -> {
            books.forEach(b -> {
                if (b.getAvailable()){
                    b.setAvailable(false);
                    records.add(new Record(b, user, days));
                }
            });
        };

        List<Book> listBooks = new ArrayList<>();
        Book bookA = new Book("Book A", 4.5, true);
        Book bookB = new Book("Book B", 4.0, false);
        Book bookC = new Book("Book C", 4.3, true);
        Book bookD = new Book("Book D", 3.3, true);
        Book bookE = new Book("Book E", 3.0, false);
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

////        All Books
        System.out.println("List of Books:");
        printListOfBooks(listBooks);

////        Available Books
        System.out.println("\nList of Available Books:");
        printListOfBooks(availableBooks.check(listBooks));

////        Rent Books
        List<Book> rentedBookListA = new ArrayList<>();
        rentedBookListA.add(bookC);
        rentedBookListA.add(bookG);

        List<Book> rentedBookListB = new ArrayList<>();
        rentedBookListB.add(bookD);
        rentedBookListB.add(bookH);

        int days = 3;
        LocalDateTime initDate = LocalDateTime.now().minus(Period.ofDays(days));
        initDate = initDate.minus(Duration.ofHours(5));

        
        User userA = new User(1, "User A", "0838423658");
        User userB = new User(2, "User B", "0838254567");

        rental.rentedBook(rentedBookListA, userA, days);
        rental.rentedBook(rentedBookListB, userB, days);

        System.out.println();

        System.out.println("\nRecord of Rented Books:");
        System.out.println("------------------------");
        printRecord(records);

        System.out.println("\nList of Available Books:");
        printListOfBooks(availableBooks.check(listBooks));

////        Books with Rating > 4
        System.out.println("\nList of Books with Rating > 4:");
        List<Book> goodRating = listBooks.stream()
                .filter(b -> b.getRating() > 4.0)
                .collect(Collectors.toList());
        printListOfBooks(goodRating);

        System.out.println("\nList of Ratings > 4:");
        List<Double> ratingsMoreThanFour = goodRating.stream()
                .map(Book::getRating)
                .collect(Collectors.toList());
        System.out.println(ratingsMoreThanFour);

////        Biggest Rating
        System.out.println("\nBiggest Rating:");
        System.out.println(ratingsMoreThanFour.stream().reduce(0.0, Double::max));

////        Print Books by Year Published
        System.out.println("\nPrint Book by Year Published:");
        printListOfBooksByYearPublished(listBooks, 2019);

////          Modify unavailable books' rented period if null by implementing Optional
//        System.out.println("\nSet unavailable books with no rented period to 999");
//        List<Book> modifiedList = listBooks.stream()
//                .filter(book -> !book.getAvailable())
//                .collect(Collectors.toList());
//        printListOfBooks(modifyNullRentedPeriod(modifiedList));

////        All Books
        System.out.println("\nList of Books by biggest rating:");
        printListOfBooks(listBooks.stream()
                .sorted(Comparator.comparingDouble(Book::getRating).reversed())
                .collect(Collectors.toList()));
    }

    static void printListOfBooks(List<Book> books){
        System.out.println("Title \t Published Year \t Rating \t Available");
//                "Available \t\t Initial Rented Date \t\t\tFinal Rented Date \t\t Rented Period (Days)");
        books.forEach(System.out::println);
    }

    static void printRecord(List<Record> records) {
        records.forEach(r -> {
            LocalDateTime rentDate = r.getInitialDate();
            LocalDateTime finalDate = r.getFinalDate();
            System.out.print(r.getUser().getName()+" rent a ");
            System.out.print(r.getBook().getTitle());
            System.out.println(" for "+r.getRentedDays()+" days");
            System.out.println("From: "+rentDate.getDayOfMonth()+" "+rentDate.getMonth()+" "+rentDate.getYear()+" ~ "+rentDate.getHour()+":"+rentDate.getMinute()+":"+rentDate.getSecond());
            System.out.println("To: "+finalDate.getDayOfMonth()+" "+finalDate.getMonth()+" "+finalDate.getYear()+" ~ "+finalDate.getHour()+":"+finalDate.getMinute()+":"+finalDate.getSecond());
            long timeLeft = Duration.between(LocalDateTime.now(), finalDate).toHours();
            System.out.println(timeLeft/24+" days and "+ timeLeft%24+" hours left");
            System.out.println("Exceeded: "+r.getExceededDuration()+" seconds");
            System.out.println();
        });
    }

    static void printListOfBooksByYearPublished(List<Book> books, int year) {
        List<Book> onYear = books.stream()
                .filter(book -> book.getPublishedYear() == year)
                .collect(Collectors.toList());
        System.out.println("Books that were published in " + String.valueOf(year) + " is:");
        for (Book book : onYear) {
            System.out.println(book.getTitle());
        }
    }

//    static List<Book> modifyNullRentedPeriod(List<Book> books){
//        //List<Book> result = new ArrayList<>();
//        for (Book book : books) {
//             if (book.getRentedDays() == 0){
//                 book.setRentedDays(999);
//             }
//            book.setInitialDate(Optional.ofNullable(book.getInitialDate()).orElse(LocalDateTime.now()));
//        }
//        return books;
//    }
}
