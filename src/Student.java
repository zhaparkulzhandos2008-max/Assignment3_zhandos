import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Student extends Person {

    public Student(int id, String name) {
        super(id, name);
    }

    @Override
    public String getRole() {
        return "Student";
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "'}";
    }


    public void save() {
        String sql = "INSERT INTO students (id, name) VALUES (?, ?) ON CONFLICT (id) DO NOTHING";
        try (Connection c = DB.connect();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, this.id);
            ps.setString(2, this.name);
            ps.executeUpdate();
            System.out.println("Added: " + this);
        } catch (SQLException e) {
            System.out.println("Error saving student: " + this);
            e.printStackTrace();
        }
    }

    public static void getAll() {
        String sql = "SELECT * FROM students";
        try (Connection c = DB.connect();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\nStudents in DB:");
            while (rs.next()) {
                System.out.println("id=" + rs.getInt("id") + ", name=" + rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateName(String newName) {
        String sql = "UPDATE students SET name=? WHERE id=?";
        try (Connection c = DB.connect();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, newName);
            ps.setInt(2, this.id);
            ps.executeUpdate();
            this.name = newName;
            System.out.println("Updated student: " + this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        String sql = "DELETE FROM students WHERE id=?";
        try (Connection c = DB.connect();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, this.id);
            ps.executeUpdate();
            System.out.println("Deleted student: " + this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
