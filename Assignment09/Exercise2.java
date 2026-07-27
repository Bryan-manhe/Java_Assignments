/**
 * Assignment 09 - Exercise 2
 * Library Book Management program where the book ISBN is declared
 * as final and cannot be changed once assigned. Displays book details.
 */

class LibraryBook {
    // Final variable - ISBN cannot be changed
    private final String isbn;
    private String title;
    private String author;
    private double price;

    // Static final variable (constant) - shared across all books
    public static final String LIBRARY_NAME = "Central Library";
    public static final String LIBRARY_LOCATION = "Main Campus";

    public LibraryBook(String isbn, String title, String author, double price) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // Final method - cannot be overridden by subclasses
    public final String getISBN() {
        return isbn;
    }

    public void displayBookInfo() {
        System.out.println("==================================");
        System.out.println("Library: " + LIBRARY_NAME + " | " + LIBRARY_LOCATION);
        System.out.println("==================================");
        System.out.println("ISBN: " + isbn + " (final - cannot be changed)");
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Price: $" + String.format("%.2f", price));
        System.out.println("==================================\n");
    }

    public void updatePrice(double newPrice) {
        if (newPrice > 0) {
            this.price = newPrice;
            // isbn = "NEW-ISBN"; // COMPILATION ERROR! Cannot modify final field
        }
    }
}

// Subclass demonstrating that final method cannot be overridden
class ReferenceBook extends LibraryBook {
    private int edition;

    public ReferenceBook(String isbn, String title, String author, double price, int edition) {
        super(isbn, title, author, price);
        this.edition = edition;
    }

    public void displayRefBookInfo() {
        displayBookInfo();
        System.out.println("Edition: " + edition);
        System.out.println("(Reference books cannot be borrowed out)");
    }

    // Uncommenting below would cause compilation error:
    // public String getISBN() { return "OVERRIDE"; } // Cannot override final method
}

public class Exercise2 {
    public static void main(String[] args) {
        System.out.println("=== LIBRARY BOOK MANAGEMENT SYSTEM ===\n");

        // Display library constant
        System.out.println("Library: " + LibraryBook.LIBRARY_NAME);
        System.out.println("Location: " + LibraryBook.LIBRARY_LOCATION + "\n");

        // Create books with final ISBN
        LibraryBook book1 = new LibraryBook("978-0-13-468599-1", "Introduction to Java", "John Smith", 59.99);
        LibraryBook book2 = new LibraryBook("978-0-13-652010-8", "Data Structures & Algorithms", "Jane Doe", 45.50);
        LibraryBook book3 = new LibraryBook("978-0-13-468739-9", "Database Systems", "Bob Wilson", 55.00);

        System.out.println("--- Book Records ---");
        book1.displayBookInfo();
        book2.displayBookInfo();
        book3.displayBookInfo();

        // Demonstrate that ISBN is truly final
        System.out.println("--- Attempt to Modify Final Fields (Demonstration) ---");
        System.out.println("ISBN for book1: " + book1.getISBN());
        book1.updatePrice(49.99); // price can change
        System.out.println("After price update:");
        book1.displayBookInfo();
        System.out.println("(Note: ISBN remains unchanged - 'final' prevents modification)");

        // Using subclass (demonstrates final method can't be overridden)
        System.out.println("--- Reference Book (Subclass) ---");
        ReferenceBook refBook = new ReferenceBook("978-0-13-468599-2",
            "Java Complete Reference", "Herbert Schildt", 69.99, 12);
        refBook.displayRefBookInfo();

        System.out.println("=== KEY CONCEPTS ===");
        System.out.println("1. final variable: isbn - cannot be reassigned");
        System.out.println("2. static final constant: LIBRARY_NAME, LIBRARY_LOCATION");
        System.out.println("3. final method: getISBN() - cannot be overridden");
        System.out.println("(Uncommenting override would show compilation error)");
    }
}
