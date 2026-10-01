import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LibraryBookInformation{
    public static void main(String[] args){
        try{
            FileWriter writer = new FileWriter("book.txt");
            writer.write("====================================\n");
            writer.write("    Book1 Details    \n");
            writer.write("Book ID      : B001\n");
            writer.write("Book Title   : Java Programming\n");
            writer.write("Author       : John Doe\n");
            writer.write("====================================\n");

             writer.write("    Book2 Details    \n");
            writer.write("Book ID      : B002\n");
            writer.write("Book Title   : Python Programming\n");
            writer.write("Author       : Jane Smith\n");
             writer.write("====================================\n");

            writer.write("    Book3 Details    \n");
            writer.write("Book ID      : B003\n");
            writer.write("Book Title   : Data Structures\n");
            writer.write("Author       : Bob Johnson\n");
            writer.write("====================================\n");

            writer.close();
            System.out.println("Data written to book.txt successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
        }
        try{
            FileReader reader = new FileReader("book.txt");
            int character;
            System.out.println("Contents of book.txt:");
            while ((character = reader.read()) != -1) {
                System.out.print((char) character);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading from the file.");
        }
    }
}