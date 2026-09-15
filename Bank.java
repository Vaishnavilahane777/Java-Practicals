//Practical 1 PS.2
class BankAccount{
    int accountNumber;
    String AccountHolderName;
    float balance,deposit,withdrawn;

    BankAccount(int id, String n, float b, float d, float w){
        accountNumber=id;
        AccountHolderName=n;
        balance=b;
        deposit=d;
        withdrawn=w;
    }
    
    void display(){
        System.out.println("Account Number: "+accountNumber);
        System.out.println("Account Holder Name: "+AccountHolderName);
        System.out.println("Account Initial balance: "+balance);
        System.out.println("Deposit money: "+deposit);
        System.out.println("Account withdrawn: "+withdrawn);
        balance=balance+deposit-withdrawn;
        System.out.println("Account balance: "+balance);
    }
}

public class Bank{
    public static void main(String[] args){
        BankAccount b1=new BankAccount(123,"ABC",100000,2000,100);
        BankAccount b2=new BankAccount(456,"DEF",50000,200,0);
        b1.display();
        System.out.println();
        b2.display();    
}
}