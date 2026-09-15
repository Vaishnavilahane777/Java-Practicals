//Practical1 PS.1
class Employee {
    int employeeID;
    String employeeName;
    float salary;

    Employee(int id, String n, float s) {
        this.employeeID = id;
        this.employeeName = n;
        this.salary = s;
    }

    void display() {
        System.out.println("Employee ID : " + employeeID);
        System.out.println("Employee Name : " + employeeName);
        System.out.println("Salary : " + salary);
    }
} // End of Employee class

public class EmployeeInfo {
    public static void main(String[] args) {
        Employee e1 = new Employee(101, "ABC", 50000);
        Employee e2 = new Employee(102, "XYZ", 20000);

        e1.display();
        System.out.println();
        e2.display();
    }
}