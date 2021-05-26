import java.util.Scanner;

public class Manager {

    /*
     this is the main manager...
     its his responsibility to authenticate the transactions
     and keep data available...
     this should ideally run on a thread independent of the main function
     will implement in newer versions
    */

    AccountsHashMap db = new AccountsHashMap();

    void loop(){
        System.out.println("MENU CARD");
        System.out.println("1. Open a new account");
        System.out.println("2. Check balance");
        System.out.println("3. Add Money");
        System.out.println("4. Withdraw Money");
        System.out.println("0. Exit the console");

        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        boolean loop = true;

        // Archit, ideally this should close the connection of the current user
        // and not the manager, but as of now its a cmd application and thus I
        // would end the manager
        switch (i) {
            case 1 -> makeAcc();
            case 2 -> getBalance();
            case 3 -> depositMoney();
            case 4 -> withdrawMoney();
            case 0 -> loop = false;
            default -> System.out.println("Sorry, Invalid input");
        }
        if (loop)
            loop();
    }

    public void makeAcc(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your Name");
        String name = sc.nextLine();

        System.out.println("Enter your MobNo");
        String mobNo = sc.nextLine();

        System.out.println("Enter the amount you would like to deposit");
        long balance = sc.nextLong();

        System.out.println("Cool, let us process");
        Account acc = new Account(name, mobNo, Long.toString(balance));

        // acc created and add it into hashmap/db for other ops
        db.insert(acc.bankAccNo, acc);
    }

    private Account getAccount(){
        System.out.println("Hii, pass in your Acc. No.");
        Scanner sc = new Scanner(System.in);

        String accNo = sc.nextLine();
        Account acc = db.fetch(accNo);

        if (acc == null){
            System.out.println("Sorry bro, check your acc no., try again");
            return getAccount();
        }
        return acc;
    }

    public String getPin(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Cool, now tell your password");
        String pin = sc.nextLine();
        System.out.println("Badhiya, let me check...");

        return pin;
    }

    public long getAmount(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the amount");

        return sc.nextLong();
    }

    public void getBalance(){

        Account acc = getAccount();
        String pin = getPin();

        String balance = acc.getBalance(pin);
        if (balance == null) {
            System.out.println("Ahh... wrong password bro..., try again...");
        } else {
            System.out.println("Here is your balance :- " + balance);
        }

    }

    public void depositMoney(){
        Account acc = getAccount();
        String pin = getPin();
        long amount = getAmount();

        acc.depositMoney(pin, amount);

    }

    public void withdrawMoney(){
        Account acc = getAccount();
        String pin = getPin();
        long amount = getAmount();

        acc.withdrawMoney(pin, amount);
    }

}
