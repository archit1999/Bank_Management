import java.util.HashMap;

public class AccountsHashMap implements StorageManager{

    // db final coz' we can update it but we cant change the hashmap towards
    // which it points...

    private final HashMap<String, Account> db;

    AccountsHashMap(){
        this.db = new HashMap<>();
    }

    @Override
    public String insertAcc(String name, String mobNo, String initBalance, String pin) {
        Account acc = new Account(name, mobNo, initBalance, pin);
        db.put(acc.bankAccNo, acc);
        return acc.bankAccNo;
    }

    @Override
    public String fetchBalance(String accNo, String pin) {
        Account acc = db.get(accNo);
        return acc.getBalance(pin);
    }

    @Override
    public String depositMoney(String accNo, String amount) {
        Account acc = db.get(accNo);
        return acc.depositMoney(amount);
    }

    @Override
    public String withdrawMoney(String accNo, String pin, String amount) {
        Account acc = db.get(accNo);
        return acc.withdrawMoney(pin, amount);
    }
}

