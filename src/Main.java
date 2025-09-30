import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main  {
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/fittrack",
                    "root",
                    "adiladil123"
            );
            System.out.println("✅ Connexion réussie !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
