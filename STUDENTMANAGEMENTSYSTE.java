

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Student implements Serializable {

    // Constructors, getters, and setters

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    // Getters and Setters

    private String name;
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    //////////////////////////////////////////////////////////////
    private int rollNumber;
    public int getRollNumber()
    {
        return rollNumber;
    }
    public void setRollNumber(int rollNumber)
    {
        this.rollNumber = rollNumber;
    }

    /////////////////////////////////////////////////////////////
    private String grade;
    public String getGrade()
    {
        return grade;
    }
    public void setGrade(String grade)
    {
        this.grade = grade;
    }
/////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", rollNumber=" + rollNumber +
                ", grade='" + grade + '\'' +
                '}';
    }
}

class StudentManagementSystem {
    private ArrayList<Student> students;

    public StudentManagementSystem() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(int rollNumber) {
        students.removeIf(student -> student.getRollNumber() == rollNumber);
    }

    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    public void displayAllStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void saveToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(students);
            System.out.println("Student data saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            students = (ArrayList<Student>) ois.readObject();
            System.out.println("Student data loaded from " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

public class StudentManagementSystemApp {
    public static void main(String[] args) {

        System.out.println("***************************************");
        System.out.println(" WELCOME TO STUDENT MANAGEMENT SYSTEM");
        System.out.println("***************************************");

        Scanner scanner = new Scanner(System.in);
        StudentManagementSystem system = new StudentManagementSystem();

        while (true) {
            System.out.println("\nStudent Management System Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search for Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Save Student Data to File");
            System.out.println("6. Load Student Data from File");
            System.out.println("7. Exit");

            while (true) {

                System.out.print("Enter your choice (1-7): ");
                int choice = scanner.nextInt();

                if (choice == 1)
                {
                    addStudent(scanner, system);
                }
                else if (choice == 2)
                {
                    removeStudent(scanner, system);
                }
                else if (choice == 3)
                {
                    searchStudent(scanner, system);
                }
                else if (choice == 4)
                {
                    system.displayAllStudents();
                }
                else if (choice == 5)
                {
                    saveToFile(scanner, system);
                }
                else if (choice == 6)
                {
                    loadFromFile(scanner, system);
                }
                else if (choice == 7)
                {
                    System.out.println("Exiting Student Management System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                }
                else
                {
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                }
            }
        }
    }

    private static void addStudent(Scanner scanner, StudentManagementSystem system) {
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter student roll number: ");
        int rollNumber = scanner.nextInt();

        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter student grade: ");
        String grade = scanner.nextLine();

        Student student = new Student(name, rollNumber, grade);
        system.addStudent(student);
        System.out.println("Student added successfully.");
    }

    private static void removeStudent(Scanner scanner, StudentManagementSystem system) {
        System.out.print("Enter the roll number of the student to remove: ");
        int rollNumber = scanner.nextInt();

        system.removeStudent(rollNumber);
        System.out.println("Student removed successfully.");
    }

    private static void searchStudent(Scanner scanner, StudentManagementSystem system) {
        System.out.print("Enter the roll number of the student to search: ");
        int rollNumber = scanner.nextInt();

        Student student = system.searchStudent(rollNumber);
        if (student != null) {
            System.out.println("Student found:\n" + student);
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void saveToFile(Scanner scanner, StudentManagementSystem system) {
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter the file name to save student data: ");
        String fileName = scanner.nextLine();

        system.saveToFile(fileName);
    }

    private static void loadFromFile(Scanner scanner, StudentManagementSystem system) {
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter the file name to load student data: ");
        String fileName = scanner.nextLine();

        system.loadFromFile(fileName);
    }
}
