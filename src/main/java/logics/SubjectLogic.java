package logics;
import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;
public class SubjectLogic {
    public void work(SessionFactory sessionFactory) {
        System.out.println("Введите 1 для создания предмета");
        System.out.println("Введите 2 для чтения предмета");
        System.out.println("Введите 3 для изменения предмета");
        System.out.println("Введите 4 для удаления предмета");
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
        System.out.println("Введите название предмета");
        String name = scanner.next();
        System.out.println("Введите часы");
        int hours = scanner.nextInt();

        Subject subject = new Subject(name, hours);
        session.save(subject);
    }

    private void read(Session session) {
        List<Subject> subject = session.createQuery("SELECT a from Subject a", Subject.class).getResultList();
        System.out.println(subject);
    }

    private void update(Session session) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите id предмета");
        int id = scanner.nextInt();

        System.out.println("Введите название предмета");
        String name = scanner.next();
        System.out.println("Введите часы");
        int hours = scanner.nextInt();

        Subject subject = session.get(Subject.class, id);
        subject.setSubjectName(name);
        subject.setHours(hours);
        session.save(subject);
    }

    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id предмета");
        int id = scanner.nextInt();

        Subject subject = session.get(Subject.class, id);
        session.delete(subject);
    }

    private void filterRead(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите 1 для фильтрации по названию");
        System.out.println("Введите 2 для фильтрации по часам");
        int numb = scanner.nextInt();
        List<Subject> subject = null;
        switch(numb) {
            case 1:
                System.out.println("Введите название");
                String name = scanner.next();
                subject = session.createQuery("SELECT a from Subject a where subjectName = \'" + name + "\'", Subject.class).getResultList();
                break;
            case 2:
                System.out.println("Введите часы");
                int hours = scanner.nextInt();
                subject = session.createQuery("SELECT a from Subject a where hours = \'" + hours + "\'", Subject.class).getResultList();
                break;
        }
        System.out.println(subject);
    }
}