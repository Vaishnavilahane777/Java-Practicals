import java.util.*;

public class EmployeeRecord {
    public static void main(String[] args) {
        ArrayList<String> employees = new ArrayList<>();
        employees.add("Rahul");
        employees.add("Priya");
        employees.add("Amit");
        employees.add("Sneha");
        employees.add("Rohit");
        System.out.println("List of Employees: " + employees);

    
        TreeSet<Integer> salaries = new TreeSet<>();
        salaries.add(50000);
        salaries.add(60000);
        salaries.add(70000);
        salaries.add(55000);
        salaries.add(65000);
        System.out.println("\nEmployee Salaries: " + salaries);
        System.out.println("Lowest Salary: " + salaries.first());
        System.out.println("Highest Salary: " + salaries.last());


        HashMap<Integer, String> employeeIds = new HashMap<>();
        employeeIds.put(101, "Rahul");
        employeeIds.put(102, "Priya");
        employeeIds.put(103, "Amit");
        employeeIds.put(104, "Sneha");
        employeeIds.put(105, "Rohit");
        System.out.println("\nEmployee IDs Map: " + employeeIds);
        System.out.println("Employee with ID=103: " + employeeIds.get(103));
        System.out.println("Employee with ID=104: " + employeeIds.get(104));
        System.out.println("Employee with ID=105: " + employeeIds.get(105));
        System.out.println("\n");
    }
}
