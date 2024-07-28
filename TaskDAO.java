import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {
    public static void addTask(Task task) {
        String sql = "INSERT INTO tasks(title, description, due_date, priority) VALUES(?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, task.getTitle());
            pstmt.setString(2, task.getDescription());
            pstmt.setString(3, task.getDueDate());
            pstmt.setInt(4, task.getPriority());
            pstmt.executeUpdate();
            System.out.println("Task added: " + task.getTitle()); // Debug statement
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks";

        try (Connection conn = Database.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setDueDate(rs.getString("due_date"));
                task.setPriority(rs.getInt("priority"));
                tasks.add(task);
            }
            System.out.println("Tasks retrieved: " + tasks.size()); // Debug statement
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    public static void updateTask(Task task) {
        String sql = "UPDATE tasks SET title = ?, description = ?, due_date = ?, priority = ? WHERE id = ?";

        try (Connection conn = Database.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, task.getTitle());
            pstmt.setString(2, task.getDescription());
            pstmt.setString(3, task.getDueDate());
            pstmt.setInt(4, task.getPriority());
            pstmt.setInt(5, task.getId());
            pstmt.executeUpdate();
            System.out.println("Task updated: " + task.getTitle()); // Debug statement
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteTask(int id) {
        String sql = "DELETE FROM tasks WHERE id = ?";

        try (Connection conn = Database.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Task deleted: " + id); // Debug statement
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
