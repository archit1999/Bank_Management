// Any class which is gonna be used as storage should implement this
// interface.

public interface StorageManager {

    String insertAcc(String Name, String mobNo, String initBalance, String  pin);   // returns accNo
    String fetchBalance(String accNo, String pin);                                  // returns Balance
    String depositMoney(String accNo, String amount);                               // returns success or failure
    String withdrawMoney(String accNo, String pin, String amount);                  // returns balance

}
