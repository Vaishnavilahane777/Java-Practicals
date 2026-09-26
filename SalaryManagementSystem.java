class SalaryEmployee {
    int salary;

    void calculateSalary() {
        System.out.println("Calculating salary for Employee");
        System.out.println("Salary: " + salary);
    }
}

class SalaryManager extends SalaryEmployee {
    @Override
    void calculateSalary() {
        System.out.println("Manager gets a bonus of 20%");
        System.out.println("Salary for Manager with bonus: " + (salary + (salary * 0.2)));
    }
}

class SalaryDeveloper extends SalaryEmployee {
    @Override
    void calculateSalary() {
        System.out.println("Developer gets overtime pay of 40%");
        System.out.println("Salary for Developer with overtime: " + (salary + (salary * 0.4)));
    }
}

public class SalaryManagementSystem {
    public static void main(String[] args) {
        SalaryEmployee emp = new SalaryEmployee();
        SalaryEmployee mgr = new SalaryManager();
        SalaryEmployee dev = new SalaryDeveloper();

        // Assign salaries
        emp.salary = 30000;
        mgr.salary = 50000;
        dev.salary = 40000;

        emp.calculateSalary();
        System.out.println();

        mgr.calculateSalary();
        System.out.println();

        dev.calculateSalary();
    }
}