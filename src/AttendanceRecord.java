import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AttendanceRecord {
    private Student student;
    private boolean present;

    public AttendanceRecord(Student student, boolean present) {
        this.student = student;
        this.present = present;
    }

    public Student getStudent() { return student; }
    public boolean isPresent() { return present; }

    @Override
    public String toString() {
        return student.getName() + " present: " + present;
    }

    public void save() {
        String sql = "INSERT INTO attendance (student_id, present) VALUES (?, ?)";
        try (Connection c = DB.connect();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, student.getId());
            ps.setBoolean(2, present);
            ps.executeUpdate();
            System.out.println("Saved attendance: " + this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getAll() {
        String sql = "SELECT a.id, s.name, a.present FROM attendance a " +
                "JOIN students s ON a.student_id = s.id";
        try (Connection c = DB.connect();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            System.out.println("\nAttendance Records:");
            while (rs.next()) {
                System.out.println("id=" + rs.getInt("id") +
                        ", student=" + rs.getString("name") +
                        ", present=" + rs.getBoolean("present"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
