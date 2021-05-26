import java.util.HashMap;

public class AccountsHashMap {

    HashMap<String, Account> db;

    AccountsHashMap(){
        this.db = new HashMap<>();
    }

    void insert(String accNo, Account acc){
        db.put(accNo, acc);
    }

    Account fetch(String accNo){
        return db.get(accNo);
    }
}

