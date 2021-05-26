public class Account {

    // it can share acc no but never pin and balance and all

    public final String bankAccNo;
    private String accBalance;
    private final String accHolderName;
    private final String mobNo;
    private final String pin;

    private long lastAccNo = 99999999L;
    private long lastPw = 15081999L;

    Account(String name, String mobNo, String firstDeposit){
        this.accHolderName = name;
        this.mobNo = mobNo;
        this.accBalance = firstDeposit;

        this.bankAccNo = randomAccNoGen();
        this.pin = passwordGen();
        showAccNoAndPin();
    }

    private String randomAccNoGen(){
        this.lastAccNo++ ;
        return Long.toString(this.lastAccNo);
    }

    private String passwordGen(){
        this.lastPw++;
        return Long.toString(this.lastPw);
    }

    private void showAccNoAndPin(){
        // will be called only once when acc is created, to let user note down pin
        System.out.println("kindly note your Acc no & pin :- " + this.bankAccNo + " " + this.pin);
    }

    public String getBalance(String password){
        if (password.equals(this.pin))
            return accBalance;
        return null;
    }

    public boolean withdrawMoney(String password, Long amount){
        Long balance = Long.valueOf(this.accBalance);
        if (password.equals(this.pin)) {
            if (amount <= balance) {
                balance = balance - amount;
                this.accBalance = Long.toString(balance);
                return true;
            }
            System.out.println("Insufficient Balance... try again");
            return false;
        }
        System.out.println("Wrong pin... try again");
        return false;
    }

    public boolean depositMoney(String password, Long amount){
        long balance = Long.parseLong(this.accBalance);             // here primitive used coz
        if (password.equals(this.pin)){
            balance = balance + amount;                             // todo handle overflow of int
            this.accBalance = Long.toString(balance);
            return true;
        }
        System.out.println("Wrong pin... try again");
        return false;
    }

}

