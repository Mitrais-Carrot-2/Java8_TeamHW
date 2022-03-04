import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class User {
    interface Transaction{
        List<Book> pinjamBuku(List<Book> books);
    }
    interface Sort{
        List<Book> sortRating(List<Book> books);
    }
    interface Available{
        List<Book> check(List<Book> books);
    }

    public static void main(String[] args) {
        Available availableBooks = (books) -> {
            return books.stream().filter(b -> b.getAvailable()==true).collect(Collectors.toList());
        };
        Transaction borrowA = (books) -> {
            availableBooks.check(books).forEach(b -> {
                b.setAvailable(false);
            });
            return books;
        };

        List<Book> listBooks = new ArrayList<>();
        Book bookA = new Book("A", 4.5, true);
        Book bookB = new Book("B", 4.0, false);
        Book bookC = new Book("C", 4.3, true);

        listBooks.add(bookA);
        listBooks.add(bookB);
        listBooks.add(bookC);

        System.out.println("List of Books:");
        printListOfBooks(listBooks);

        System.out.println("List of available Books:");
        printListOfBooks(availableBooks.check(listBooks));

        List<Book> borrowedBooks = new ArrayList<>();
        borrowedBooks.add(bookC);
        System.out.println("List of borrowed Books:");
        printListOfBooks(borrowedBooks);

        borrowA.pinjamBuku(borrowedBooks);

        System.out.println("List of available Books:");
        printListOfBooks(availableBooks.check(listBooks));
    }

    static void printListOfBooks(List<Book> books){
        books.forEach(a -> {
            System.out.println(a.getTitle());
            System.out.println(a.getRating());
            System.out.println(a.getAvailable());
            System.out.println("------------------------");
        });
    }
}
