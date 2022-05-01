package logics;
import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;
public class DeaneryLogic {
    public void work(SessionFactory sessionFactory) {
        System.out.println("Введите 1 для создания деканата");
        System.out.println("Введите 2 для чтения деканата");
        System.out.println("Введите 3 для изменения деканата");
        System.out.println("Введите 4 для удаления деканата");
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
        System.out.println("Введите название деканата");
        String deaneryName = scanner.next();
        System.out.println("Введите фамилию декана");
        String deanSurname = scanner.next();
        System.out.println("Введите имя декана");
        String deanName = scanner.next();
        System.out.println("Введите отчество декана");
        String deanMiddlename = scanner.next();
        System.out.println("Введите номер телефона");
        int phoneNumb = scanner.nextInt();

        Deanery deanery = new Deanery(deaneryName, deanSurname, deanName, deanMiddlename, phoneNumb);
        session.save(deanery);
    }

    private void read(Session session) {
        List<Deanery> deanery = session.createQuery("SELECT a from Deanery a", Deanery.class).getResultList();
        System.out.println(deanery);
    }

    private void update(Session session) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите id деканата");
        int id = scanner.nextInt();

        System.out.println("Введите название деканата");
        String deaneryName = scanner.next();
        System.out.println("Введите фамилию декана");
        String deanSurname = scanner.next();
        System.out.println("Введите имя декана");
        String deanName = scanner.next();
        System.out.println("Введите отчество декана");
        String deanMiddlename = scanner.next();
        System.out.println("Введите номер телефона");
        int phoneNumb = scanner.nextInt();

        Deanery deanery = session.get(Deanery.class, id);
        deanery.setDeaneryName(deaneryName);
        deanery.setDeanName(deanName);
        deanery.setDeanSurname(deanSurname);
        deanery.setDeanMiddlename(deanMiddlename);
        deanery.setPhone(phoneNumb);
        session.save(deanery);
    }

    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id деканата");
        int id = scanner.nextInt();

        Deanery deanery = session.get(Deanery.class, id);
        session.delete(deanery);
    }

    private void filterRead(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите 1 для фильтрации по названию");
        System.out.println("Введите 2 для фильтрации по номеру телефона");
        int numb = scanner.nextInt();
        List<Deanery> deanery = null;
        switch(numb) {
            case 1:
                System.out.println("Введите название деканата");
                String name = scanner.next();
                deanery = session.createQuery("SELECT a from Deanery a where deaneryName = \'" + name + "\'", Deanery.class).getResultList();
                break;
            case 2:
                System.out.println("Введите номер телефона");
                int phoneNumb = scanner.nextInt();
                deanery = session.createQuery("SELECT a from Deanery a where phone = \'" + phoneNumb + "\'", Deanery.class).getResultList();
                break;
        }
        System.out.println(deanery);
    }
}