// Any class which is gonna be used as storage should implement this
// interface.

public interface Storage {

    void insert(String AccNo, Account Acc);
    Account fetch(String AccNo);

}
