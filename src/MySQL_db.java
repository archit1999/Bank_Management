import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL_db {

    String ip, port, tableName, user, password;

    public MySQL_db() throws ClassNotFoundException, SQLException {
        ip = "localhost";
        port = "3306";
        tableName = "demo_table";
        user = "root";
        password = "1archit@kcm";

        String url = "jdbc:mysql://" + ip + ":" + port + "/" + tableName;

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, user, password);
    }

    private String generateAccNo(){
        return "1234";
    }

    public String insert(String name, String mobNo, String balance, String pin){
        String accNo = generateAccNo();
        String query = "Insert into " + tableName + "values(" + accNo + "," + name + "," + mobNo + "," + balance + "," + pin + ");";
        return null;
    }

    public String[] fetch(String accNo, String pin){
        String query = "Select * from " + tableName + "where accNo = \"" + accNo + "\";";
        return null;
    }

    public String update(String accNo, String amount){
        String query = "update " + tableName + "set money";
        return null;
    }
}
