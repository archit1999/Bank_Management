import java.util.HashMap;

public class AccountsHashMap implements Storage {

    // db final coz' we can update it but we cant change the hashmap towards
    // which it points...

    private final HashMap<String, Account> db;

    AccountsHashMap(){
        this.db = new HashMap<>();
    }

    @Override
    public void insert(String accNo, Account acc){
        db.put(accNo, acc);
    }

    @Override
    public Account fetch(String accNo){
        return db.get(accNo);
    }
}

