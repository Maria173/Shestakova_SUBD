package logics;
import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;
public class StudentLogic {
    public void work(SessionFactory sessionFactory) {
        System.out.println("Введите 1 для создания студента");
        System.out.println("Введите 2 для чтения студента");
        System.out.println("Введите 3 для изменения студента");
        System.out.println("Введите 4 для удаления студента");
        System.out.println("Введите 5 для фильтрации");

        Scanner scanner = new Scanner(System.in);
        int numb = scanner.nextInt();
        Session session = null;
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        switch (numb) {
            case 1:
                create(session);
                break;
            case 2:
                read(session);
                break;
            case 3:
                update(session);
                break;
            case 4:
                delete(session);
                break;
            case 5:
                filterRead(session);
                break;
        }
        session.getTransaction().commit();
    }
    private void create(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите фамилию студента");
        String studentSurname = scanner.next();
        System.out.println("Введите имя студента");
        String studentName = scanner.next();
        System.out.println("Введите отчество студента");
        String studentMiddlename = scanner.next();
        System.out.println("Введите дату рождения");
        String birthdate = scanner.next();
        java.util.Date myDate = new java.util.Date(birthdate);
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        System.out.println("Введите номер группы");
        int idGroup = scanner.nextInt();

        Student student = new Student(studentSurname, studentName, studentMiddlename, sqlDate, session.get(Group_Class.class,idGroup));
        session.save(student);
    }

    private void read(Session session) {
        List<Student> student = session.createQuery("SELECT a from Student a", Student.class).getResultList();
        System.out.println(student);
    }

    private void update(Session session) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите id студента");
        int id = scanner.nextInt();

        System.out.println("Введите фамилию студента");
        String studentSurname = scanner.next();
        System.out.println("Введите имя студента");
        String studentName = scanner.next();
        System.out.println("Введите отчество студента");
        String studentMiddlename = scanner.next();
        System.out.println("Введите дату рождения");
        String birthdate = scanner.next();
        java.util.Date myDate = new java.util.Date(birthdate);
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        System.out.println("Введите номер группы");
        int idGroup = scanner.nextInt();

        Student student = session.get(Student.class, id);
        student.setStudentSurname(studentSurname);
        student.setStudentName(studentName);
        student.setStudentMiddlename(studentMiddlename);
        student.setBirthdate(sqlDate);
        student.setGroup(session.get(Group_Class.class, idGroup));
        session.save(student);
    }

    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id студента");
        int id = scanner.nextInt();

        Student student = session.get(Student.class, id);
        session.delete(student);
    }

    private void filterRead(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите 1 для фильтрации по фамилии");
        System.out.println("Введите 2 для фильтрации по имени");
        System.out.println("Введите 3 для фильтрации по отчеству");
        System.out.println("Введите 4 для фильтрации по дате рождения");
        System.out.println("Введите 5 для фильтрации по номеру группы");
        int numb = scanner.nextInt();
        List<Student> student = null;
        switch(numb) {
            case 1:
                System.out.println("Введите фамилию");
                String surname = scanner.next();
                student = session.createQuery("SELECT a from Student a where studentSurname = \'" + surname + "\'", Student.class).getResultList();
                break;
            case 2:
                System.out.println("Введите имя");
                String name = scanner.next();
                student = session.createQuery("SELECT a from Student a where studentName = \'" + name + "\'", Student.class).getResultList();
                break;
            case 3:
                System.out.println("Введите отчество");
                String middlename = scanner.next();
                student = session.createQuery("SELECT a from Student a where studentMiddlename = \'" + middlename + "\'", Student.class).getResultList();
                break;
            case 4:
                System.out.println("Введите дату рождения");
                int date = scanner.nextInt();
                java.util.Date myDate = new java.util.Date(date);
                java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
                student = session.createQuery("SELECT a from Student a where birthdate = \'" + sqlDate + "\'", Student.class).getResultList();
                break;
            case 5:
                System.out.println("Введите номер группы");
                int idGroup = scanner.nextInt();
                student = session.createQuery("SELECT a from Student a where group = \'" + idGroup + "\'", Student.class).getResultList();
                break;
        }
        System.out.println(student);
    }
}