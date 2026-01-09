public class Main {
    public static void main(String[] args) {

        Student s1 = new Student(1, "Aruzhan");
        Student s2 = new Student(2, "Dias");
        Student s3 = new Student(3, "Aliya");

        Teacher t1 = new Teacher(100, "Mr. Ivanov", "Java Programming");

        AttendanceRecord r1 = new AttendanceRecord(s1, true);
        AttendanceRecord r2 = new AttendanceRecord(s2, false);
        AttendanceRecord r3 = new AttendanceRecord(s3, true);

        // --- Сохраняем студентов и посещаемость ---
        s1.save();
        s2.save();
        s3.save();

        r1.save();
        r2.save();
        r3.save();

        // --- Выводим из БД ---
        Student.getAll();
        AttendanceRecord.getAll();

        // --- Полиморфизм и фильтры ---
        Person p1 = s1;
        Person p2 = t1;
        System.out.println("\nRoles:");
        System.out.println(p1.getRole());
        System.out.println(p2.getRole());

        Student[] students = {s1, s2, s3};

        System.out.println("\nFiltered students (name starts with 'A'):");
        for (Student s : students) {
            if (s.getName().startsWith("A")) {
                System.out.println(s);
            }
        }

        System.out.println("\nSorted students by name:");
        for (int i = 0; i < students.length - 1; i++) {
            for (int j = 0; j < students.length - i - 1; j++) {
                if (students[j].getName().compareTo(students[j + 1].getName()) > 0) {
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }
        for (Student s : students) {
            System.out.println(s);
        }
    }
}
