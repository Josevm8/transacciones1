package service.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaDb {

    private String db = "transacciones";

    public Connection getConnection() {
        Connection cn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            cn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + db
                    + "?useServerPrepStmts=true", "root", "56789");
        } catch (SQLException e) {
        } catch (Exception e) {
        }

        return cn;
    }

    public ConectaDb() {
    }
}

