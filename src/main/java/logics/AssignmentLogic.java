package logics;
import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;
public class AssignmentLogic {
    public void work(SessionFactory sessionFactory) {
        System.out.println("Введите 1 для создания кафедры");
        System.out.println("Введите 2 для чтения кафедры");
        System.out.println("Введите 3 для изменения кафедры");
        System.out.println("Введите 4 для удаления кафедры");
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
        System.out.println("Введите номер кафедры");
        int idDepartment = scanner.nextInt();
        System.out.println("Введите номер преподавателя");
        int idTeacher = scanner.nextInt();
        System.out.println("Введите номер предмета");
        int idSubject = scanner.nextInt();

        Subject_Teacher_Assignment assignment = new Subject_Teacher_Assignment(session.get(Department.class,idDepartment), session.get(Subject.class,idSubject), session.get(Teacher.class,idTeacher));
        session.save(assignment);
    }

    private void read(Session session) {
        List<Subject_Teacher_Assignment> assignment = session.createQuery("SELECT a from Subject_Teacher_Assignment a", Subject_Teacher_Assignment.class).getResultList();
        System.out.println(assignment);
    }

    private void update(Session session) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите id кафедры");
        int id = scanner.nextInt();

        System.out.println("Введите номер кафедры");
        int idDepartment = scanner.nextInt();
        System.out.println("Введите номер преподавателя");
        int idTeacher = scanner.nextInt();
        System.out.println("Введите номер предмета");
        int idSubject = scanner.nextInt();

        Subject_Teacher_Assignment assignment = session.get(Subject_Teacher_Assignment.class, id);
        assignment.setDepartment(session.get(Department.class, idDepartment));
        assignment.setSubject(session.get(Subject.class, idSubject));
        assignment.setTeacher(session.get(Teacher.class, idTeacher));
        session.save(assignment);
    }

    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id распределения");
        int id = scanner.nextInt();

        Subject_Teacher_Assignment assignment = session.get(Subject_Teacher_Assignment.class, id);
        session.delete(assignment);
    }

    private void filterRead(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите 1 для фильтрации по номеру кафедры");
        System.out.println("Введите 2 для фильтрации по номеру преподавателя");
        System.out.println("Введите 3 для фильтрации по номеру предмета");
        int numb = scanner.nextInt();
        List<Subject_Teacher_Assignment> assignment = null;
        switch(numb) {
            case 1:
                System.out.println("Введите номер кафедры");
                int idDepartment = scanner.nextInt();
                assignment = session.createQuery("SELECT a from Subject_Teacher_Assignment a where department = \'" + idDepartment + "\'", Subject_Teacher_Assignment.class).getResultList();
                break;
            case 2:
                System.out.println("Введите номер преподавателя");
                int idTeacher = scanner.nextInt();
                assignment = session.createQuery("SELECT a from Subject_Teacher_Assignment a where teacher = \'" + idTeacher + "\'", Subject_Teacher_Assignment.class).getResultList();
                break;
            case 3:
                System.out.println("Введите номер предмета");
                int idSubject = scanner.nextInt();
                assignment = session.createQuery("SELECT a from Subject_Teacher_Assignment a where subject = " + idSubject , Subject_Teacher_Assignment.class).getResultList();
                break;
        }
        System.out.println(assignment);
    }
}