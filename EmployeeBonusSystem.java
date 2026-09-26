abstract class Employee 
{
    public abstract void calculateBonus();
    public void employeeDetails() 
    {
        System.out.println("Employee details are as follows:");
    }
}

class Manager extends Employee 
{
    @Override
    public void calculateBonus() 
    {
        System.out.println("Manager bonus is 20% of salary");
    }
}

class Developer extends Employee 
{
    @Override
    public void calculateBonus() 
    {
        System.out.println("Developer bonus is 10% of salary");
    }
}

public class EmployeeBonusSystem 
{
    public static void main(String[] args) 
    {
        Employee employee1 = new Manager();
        employee1.employeeDetails();
        employee1.calculateBonus();
        System.out.println();
        Employee employee2 = new Developer();
        employee2.employeeDetails();
        employee2.calculateBonus();
    }
}

