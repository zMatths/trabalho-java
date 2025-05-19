import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/sistema_malaria", "root", "140605");
    }
}
