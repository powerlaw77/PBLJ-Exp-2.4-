package view;

import controller.StudentController;
import model.Student;
import java.util.Scanner;

public class StudentView {
    public static void main(String[] args) {
        StudentController controller = new StudentController();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== STUDENT MENU ===");
            System.out.println("1. Add Student");
            System.out.println("2. View All");
            System.out.println("3. Update Marks");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.print("Enter ID, Name, Dept, Marks: ");
                    int id = sc.nextInt();
                    String name = sc.next();
                    String dept = sc.next();
                    double marks = sc.nextDouble();
                    controller.addStudent(new Student(id, name, dept, marks));
                    break;
                case 2:
                    controller.viewStudents();
                    break;
                case 3:
                    System.out.print("Enter ID and new Marks: ");
                    int sid = sc.nextInt();
                    double newMarks = sc.nextDouble();
                    controller.updateStudent(sid, newMarks);
                    break;
                case 4:
                    System.out.print("Enter ID to delete: ");
                    int did = sc.nextInt();
                    controller.deleteStudent(did);
                    break;
                case 5:
                    System.exit(0);
            }
        }
    }
}
