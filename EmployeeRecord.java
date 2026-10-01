import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class EmployeeRecord{
    public static void main(String[] args){
        try{
            FileWriter writer = new FileWriter("employee.txt");
            writer.write("====================================\n");
            writer.write("    Employee1 Details    \n");
            writer.write("Employee Name  : Vaishnavi Lahane\n");
            writer.write("Employee ID    : 24070381\n");
            writer.write("Department     : Manager\n");
            writer.write("Salary         : 50000\n");
            writer.write("====================================\n");

             writer.write("    Employee2 Details    \n");
            writer.write("Employee Name  : ABC\n");
            writer.write("Employee ID    : 123456\n");
            writer.write("Department     : IT\n");
            writer.write("Salary         : 10000\n");
             writer.write("====================================\n");

            writer.write("    Employee3 Details    \n");
            writer.write("Employee Name  : XYZ\n");
            writer.write("Employee ID    : 789012\n");
            writer.write("Department     : HR\n");
            writer.write("Salary         : 45000\n");
            writer.write("====================================\n");

            writer.close();
            System.out.println("Data written to employee.txt successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
        }
        try{
            FileReader reader = new FileReader("employee.txt");
            int character;
            System.out.println("Contents of employee.txt:");
            while ((character = reader.read()) != -1) {
                System.out.print((char) character);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading from the file.");
        }
    }
}