public class Account {

    // it can share acc no but never pin and balance and all

    public final String bankAccNo;
    private String accBalance;
    private final String accHolderName;
    private final String mobNo;
    private final String pin;

    private long lastAccNo = 99999999L;

    Account(String name, String mobNo, String firstDeposit, String pin){
        this.accHolderName = name;
        this.mobNo = mobNo;
        this.accBalance = firstDeposit;
        this.pin = pin;

        this.bankAccNo = randomAccNoGen();

    }

    private String randomAccNoGen(){
        this.lastAccNo++ ;
        return Long.toString(this.lastAccNo);
    }

    public String getBalance(String password){
        if (password.equals(this.pin))
            return accBalance;
        return "-1";
    }

    public String withdrawMoney(String password, String amount){
        long balance = Long.parseLong(this.accBalance);
        long amount1 = Long.parseLong(amount);
        if (password.equals(this.pin)) {
            if (amount1 <= balance) {
                balance = balance - amount1;
                this.accBalance = Long.toString(balance);
                return this.accBalance;
            }
            System.out.println("Insufficient Balance... try again");
            return "-3";
        }
        System.out.println("Wrong pin... try again");
        return "-1";
    }

    public String depositMoney(String amount){
        long balance = Long.parseLong(this.accBalance);             // here primitive used coz
        balance = balance + Long.parseLong(amount);                                 // todo handle overflow of int
        this.accBalance = Long.toString(balance);
        return "Success";
    }

}

