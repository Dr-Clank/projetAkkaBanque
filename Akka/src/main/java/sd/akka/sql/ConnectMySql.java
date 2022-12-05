package sd.akka.sql;

import java.sql.*;

public class ConnectMySql {

    private final String DB_URL = "jdbc:mysql://localhost:6033/proj_bank";
    private final String DB_USER = "root";
    private final String DB_PASS = "";
    protected Connection conn;

    public ConnectMySql(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.conn = DriverManager.getConnection(
                    DB_URL,
                    DB_USER,
                    DB_PASS
            );

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Connection getConn(){
        return this.conn;
    }

}
