import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void initialize() {
        String sql = "CREATE TABLE IF NOT EXISTS tasks (\n"
                + "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "    title TEXT NOT NULL,\n"
                + "    description TEXT,\n"
                + "    due_date TEXT,\n"
                + "    priority INTEGER\n"
                + ");";

        try (Connection conn = Database.getConnection();
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Database initialized."); // Debug statement
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
