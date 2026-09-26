class Bank {
    String name;
    float interest;

    void interestRate(){
        System.out.println("Different Banks give different interest rates");
    };
}

class SBI extends Bank {
    @Override
    void interestRate() {
        System.out.println(name + " Bank offers an interest rate of: " + interest + "%");
    }
}

class ICICI extends Bank {
    @Override
    void interestRate() {
        System.out.println(name + " Bank offers an interest rate of: " + interest + "%");
    }
}

class HDFC extends Bank {
    @Override
    void interestRate() {
        System.out.println(name + " Bank offers an interest rate of: " + interest + "%");
    }
}

public class BankManagementSystem {
    public static void main(String[] args) {

        Bank bank = new Bank();
        bank.interestRate();

        SBI sbi = new SBI();
        sbi.name = "SBI";
        sbi.interest = 5.5f;

        ICICI icici = new ICICI();
        icici.name = "ICICI";
        icici.interest = 6.0f;

        HDFC hdfc = new HDFC();
        hdfc.name = "HDFC";
        hdfc.interest = 6.5f;

        sbi.interestRate();
        System.out.println();

        icici.interestRate();
        System.out.println();

        hdfc.interestRate();
    }
}