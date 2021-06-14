import java.sql.SQLException;
import java.util.Scanner;

public class User {

    /*
        New Manager, its responsibility is to just take input and ask the respective
        function of the storage to perform it. Simple, plus it makes application more modular
        Like for a new StorageType, we dont have to change manager.
     */

    StorageManager dbManager;

    User(){
        try {
            dbManager = new AccountsDB();
        }
        catch(ClassNotFoundException e){
            System.out.println("Sorry, some error on our side, sql_db initialisation error");
        }
        catch(SQLException e){
            System.out.println("Sorry, some error on our side, sql exception");
        }
    }

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

    private String encrypt(String s){
        return s;
    }

    private String decrypt(String s){
        return s;
    }

    private void makeAcc(){
        System.out.println("Enter your Name");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();

        System.out.println("Enter Mob. No.");
        String mobNo = sc.nextLine();

        System.out.println("Enter opening balance");
        String balance = sc.nextLine();

        System.out.println("Enter your Name");
        String pin = sc.nextLine();
        String hashedPin = encrypt(pin);

        String accNo = dbManager.insertAcc(name, mobNo, balance, hashedPin);
        System.out.println("Cool, Acc created, note your acc no. " + accNo);

    }

    private void getBalance() {
        System.out.println("Enter your Acc. No.");
        Scanner sc = new Scanner(System.in);
        String accNo = sc.nextLine();

        System.out.println("Enter PIN");
        String pin = sc.nextLine();
        String hashedPin = encrypt(pin);

        String balance = dbManager.fetchBalance(accNo, hashedPin);
        if (balance.equals("-1")){
            System.out.println("Wrong Password, Try Again");
        }
        else if (balance.equals("-2")){
            System.out.println("Sorry, some error on our side, try again later");
        }
        else{
            System.out.println("Balance " + balance);
        }
    }

    private void depositMoney(){
        System.out.println("Enter your Acc. No.");
        Scanner sc = new Scanner(System.in);
        String accNo = sc.nextLine();

        System.out.println("Amount to deposit :-");
        String amount = sc.nextLine();

        String balance = dbManager.depositMoney(accNo, amount);

        if (balance.equals("-2")){
            System.out.println("Sorry, some error on our side, try again later");
        }
        else{
            System.out.println("Success");
        }
    }

    private void withdrawMoney(){
        System.out.println("Enter your Acc. No.");
        Scanner sc = new Scanner(System.in);
        String accNo = sc.nextLine();

        System.out.println("Enter PIN");
        String pin = sc.nextLine();
        String hashedPin = encrypt(pin);

        System.out.println("Amount to withdraw :-");
        String amount = sc.nextLine();

        String balance = dbManager.withdrawMoney(accNo, hashedPin, amount);
        try {
            switch (balance) {
                case "-1" -> System.out.println("Wrong Password, Try Again");
                case "-2" -> System.out.println("Sorry, some error on our side, try again later");
                case "-3" -> System.out.println("Insufficient balance");
                default -> System.out.println("Balance " + balance);
            }
        }
        catch (NumberFormatException e){
                System.out.println("Error in your number format");
        }
    }
}
