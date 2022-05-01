package logics;
import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;
public class GroupLogic {
    public void work(SessionFactory sessionFactory) {
        System.out.println("Введите 1 для создания группы");
        System.out.println("Введите 2 для чтения группы");
        System.out.println("Введите 3 для изменения группы");
        System.out.println("Введите 4 для удаления группы");
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
        System.out.println("Введите название группы");
        String name = scanner.next();
        System.out.println("Введите год");
        String year = scanner.next();
        java.util.Date myDate = new java.util.Date(year);
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        System.out.println("Введите номер потока");
        int idFlow = scanner.nextInt();

        Group_Class group = new Group_Class(name, sqlDate, session.get(Flow.class,idFlow));
        session.save(group);
    }

    private void read(Session session) {
        List<Group_Class> group = session.createQuery("SELECT a from Group_Class a", Group_Class.class).getResultList();
        System.out.println(group);
    }

    private void update(Session session) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите id группы");
        int id = scanner.nextInt();

        System.out.println("Введите название группы");
        String name = scanner.next();
        System.out.println("Введите год");
        String year = scanner.next();
        java.util.Date myDate = new java.util.Date(year);
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        System.out.println("Введите номер потока");
        int idFlow = scanner.nextInt();

        Group_Class group = session.get(Group_Class.class, id);
        group.setGroupName(name);
        group.setAdmissionYear(sqlDate);
        group.setFlow(session.get(Flow.class, idFlow));
        session.save(group);
    }

    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id группы");
        int id = scanner.nextInt();

        Group_Class group = session.get(Group_Class.class, id);
        session.delete(group);
    }

    private void filterRead(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите 1 для фильтрации по названию группы");
        System.out.println("Введите 2 для фильтрации по году");
        System.out.println("Введите 3 для фильтрации по номеру потока");
        int numb = scanner.nextInt();
        List<Group_Class> group = null;
        switch(numb) {
            case 1:
                System.out.println("Введите название группы");
                String name = scanner.next();
                group = session.createQuery("SELECT a from Group_Class a where groupName = \'" + name + "\'", Group_Class.class).getResultList();
                break;
            case 2:
                System.out.println("Введите год");
                String date = scanner.next();
                java.util.Date myDate = new java.util.Date(date);
                java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
                group = session.createQuery("SELECT a from Group_Class a where admissionYear = \'" + sqlDate + "\'", Group_Class.class).getResultList();
                break;
            case 3:
                System.out.println("Введите номер потока");
                int idFlow = scanner.nextInt();
                group = session.createQuery("SELECT a from Group_Class a where flow = " + idFlow , Group_Class.class).getResultList();
                break;
        }
        System.out.println(group);
    }
}