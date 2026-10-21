package com.courseapp;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final SessionFactory factory =
            HibernateUtil.getFactory();

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            boolean running = true;

            while (running) {
                System.out.println("\n===== COURSE REGISTRATION SYSTEM =====");
                System.out.println("1. Add Student");
                System.out.println("2. Add Course");
                System.out.println("3. Register Student for Course");
                System.out.println("4. View Registrations");
                System.out.println("5. Update Course");
                System.out.println("6. Remove Registration");
                System.out.println("7. View Students");
                System.out.println("8. View Courses");
                System.out.println("9. Exit");
                System.out.print("Enter choice: ");

                int choice = Integer.parseInt(sc.nextLine().trim());

                switch (choice) {
                    case 1 -> addStudent();
                    case 2 -> addCourse();
                    case 3 -> registerStudent();
                    case 4 -> viewRegistrations();
                    case 5 -> updateCourse();
                    case 6 -> removeRegistration();
                    case 7 -> viewStudents();
                    case 8 -> viewCourses();
                    case 9 -> running = false;
                    default -> System.out.println("Invalid choice!");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        } finally {
            HibernateUtil.shutdown();
            sc.close();
        }
    }

    private static void addStudent() {
        System.out.print("Enter student name: ");
        String name = sc.nextLine();

        System.out.print("Enter student email: ");
        String email = sc.nextLine();

        Transaction tx = null;

        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();

            session.persist(new Student(name, email));

            tx.commit();
            System.out.println("Student added successfully!");
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            System.out.println("Could not add student: " + e.getMessage());
        }
    }

    private static void addCourse() {
        System.out.print("Enter course title: ");
        String title = sc.nextLine();

        System.out.print("Enter duration in weeks: ");
        int duration = Integer.parseInt(sc.nextLine().trim());

        if (duration <= 0) {
            System.out.println("Duration must be positive.");
            return;
        }

        Transaction tx = null;

        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();

            session.persist(new Course(title, duration));

            tx.commit();
            System.out.println("Course added successfully!");
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            System.out.println("Could not add course: " + e.getMessage());
        }
    }

    private static void registerStudent() {
        System.out.print("Enter student ID: ");
        long studentId = Long.parseLong(sc.nextLine().trim());

        System.out.print("Enter course ID: ");
        long courseId = Long.parseLong(sc.nextLine().trim());

        Transaction tx = null;

        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();

            Student student = session.get(Student.class, studentId);
            Course course = session.get(Course.class, courseId);

            if (student == null || course == null) {
                System.out.println("Student or course ID not found.");
                tx.rollback();
                return;
            }

            Long existing = session.createQuery(
                    "select count(r) from Registration r " +
                    "where r.student.id = :sid " +
                    "and r.course.id = :cid", Long.class)
                    .setParameter("sid", studentId)
                    .setParameter("cid", courseId)
                    .getSingleResult();

            if (existing > 0) {
                System.out.println("Student is already registered for this course.");
                tx.rollback();
                return;
            }

            session.persist(new Registration(student, course));

            tx.commit();
            System.out.println("Registration successful!");
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            System.out.println("Registration failed: " + e.getMessage());
        }
    }

    private static void viewRegistrations() {
        try (Session session = factory.openSession()) {
            List<Registration> registrations = session.createQuery(
                    "from Registration r " +
                    "join fetch r.student " +
                    "join fetch r.course order by r.id",
                    Registration.class).list();

            if (registrations.isEmpty()) {
                System.out.println("No registrations found.");
                return;
            }

            System.out.println("\n--- Registration Details ---");

            for (Registration r : registrations) {
                System.out.println(
                        "Registration ID: " + r.getId()
                        + " | Student: " + r.getStudent().getName()
                        + " | Course: " + r.getCourse().getTitle()
                        + " | Date: " + r.getRegistrationDate());
            }
        } catch (Exception e) {
            System.out.println("Could not retrieve registrations: " + e.getMessage());
        }
    }

    private static void updateCourse() {
        System.out.print("Enter course ID to update: ");
        long id = Long.parseLong(sc.nextLine().trim());

        Transaction tx = null;

        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();

            Course course = session.get(Course.class, id);

            if (course == null) {
                System.out.println("Course not found.");
                tx.rollback();
                return;
            }

            System.out.print("Enter new course title: ");
            String title = sc.nextLine();

            System.out.print("Enter new duration in weeks: ");
            int duration = Integer.parseInt(sc.nextLine().trim());

            if (title.isBlank() || duration <= 0) {
                System.out.println("Enter a title and positive duration.");
                tx.rollback();
                return;
            }

            course.setTitle(title);
            course.setDuration(duration);

            tx.commit();
            System.out.println("Course updated successfully!");
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            System.out.println("Update failed: " + e.getMessage());
        }
    }

    private static void removeRegistration() {
        System.out.print("Enter registration ID to remove: ");
        long id = Long.parseLong(sc.nextLine().trim());

        Transaction tx = null;

        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();

            Registration registration =
                    session.get(Registration.class, id);

            if (registration == null) {
                System.out.println("Registration not found.");
                tx.rollback();
                return;
            }

            session.remove(registration);

            tx.commit();
            System.out.println("Registration removed successfully!");
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            System.out.println("Removal failed: " + e.getMessage());
        }
    }

    private static void viewStudents() {
        try (Session session = factory.openSession()) {
            List<Student> students = session.createQuery(
                    "from Student order by id", Student.class).list();

            System.out.println("\n--- Students ---");

            for (Student s : students) {
                System.out.println(
                        "ID: " + s.getId()
                        + " | Name: " + s.getName()
                        + " | Email: " + s.getEmail());
            }

            if (students.isEmpty()) System.out.println("No students found.");
        }
    }

    private static void viewCourses() {
        try (Session session = factory.openSession()) {
            List<Course> courses = session.createQuery(
                    "from Course order by id", Course.class).list();

            System.out.println("\n--- Courses ---");

            for (Course c : courses) {
                System.out.println(
                        "ID: " + c.getId()
                        + " | Title: " + c.getTitle()
                        + " | Duration: " + c.getDuration() + " weeks");
            }

            if (courses.isEmpty()) System.out.println("No courses found.");
        }
    }
}
