import java.util.*;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        ArrayList<String> books = new ArrayList<>();
        books.add("The Great Gatsby");
        books.add("To Kill a Mockingbird");
        books.add("1984");
        books.add("Pride and Prejudice");
        books.add("The Hobbit");
        System.out.println("List of Books: " + books);

        TreeSet<Integer> bookIds = new TreeSet<>();
        bookIds.add(105);
        bookIds.add(101);
        bookIds.add(103);
        bookIds.add(102);
        bookIds.add(104);
        System.out.println("\nBook IDs: " + bookIds);
        System.out.println("Lowest Book ID: " + bookIds.first());
        System.out.println("Highest Book ID: " + bookIds.last());

        HashMap<Integer, String> bookCatalog = new HashMap<>();
        bookCatalog.put(101, "The Great Gatsby");
        bookCatalog.put(102, "To Kill a Mockingbird");
        bookCatalog.put(103, "1984");
        bookCatalog.put(104, "Pride and Prejudice");
        bookCatalog.put(105, "The Hobbit");
        System.out.println("\nBook Catalog: " + bookCatalog);
        System.out.println("Book with ID=103: " + bookCatalog.get(103));
        System.out.println("Book with ID=104: " + bookCatalog.get(104));
        System.out.println("Book with ID=105: " + bookCatalog.get(105));
    }
}
