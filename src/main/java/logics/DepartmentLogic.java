package logics;
import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;
public class DepartmentLogic {
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
        System.out.println("Введите название кафедры");
        String name = scanner.next();
        System.out.println("Введите номер телефона");
        int phone = scanner.nextInt();
        System.out.println("Введите номер деканата");
        int idDeanery = scanner.nextInt();

        Department department = new Department(name, phone, session.get(Deanery.class,idDeanery));
        session.save(department);
    }

    private void read(Session session) {
        List<Department> department = session.createQuery("SELECT a from Department a", Department.class).getResultList();
        System.out.println(department);
    }

    private void update(Session session) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите id кафедры");
        int id = scanner.nextInt();

        System.out.println("Введите название кафедры");
        String name = scanner.next();
        System.out.println("Введите номер телефона");
        int phone = scanner.nextInt();
        System.out.println("Введите номер деканата");
        int idDeanery = scanner.nextInt();

        Department department = session.get(Department.class, id);
        department.setDepartmentName(name);
        department.setPhone(phone);
        department.setDeanery(session.get(Deanery.class, idDeanery));
        session.save(department);
    }

    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id кафедры");
        int id = scanner.nextInt();

        Department department = session.get(Department.class, id);
        session.delete(department);
    }

    private void filterRead(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите 1 для фильтрации по названию кафедры");
        System.out.println("Введите 2 для фильтрации по номеру телефона");
        System.out.println("Введите 3 для фильтрации по номеру деканата");
        int numb = scanner.nextInt();
        List<Department> department = null;
        switch(numb) {
            case 1:
                System.out.println("Введите название кафедры");
                String name = scanner.next();
                department = session.createQuery("SELECT a from Department a where departmentName = \'" + name + "\'", Department.class).getResultList();
                break;
            case 2:
                System.out.println("Введите номер телефона");
                int phone = scanner.nextInt();
                department = session.createQuery("SELECT a from Department a where phone = \'" + phone + "\'", Department.class).getResultList();
                break;
            case 3:
                System.out.println("Введите номер деканата");
                int idDeanery = scanner.nextInt();
                department = session.createQuery("SELECT a from Department a where deanery = " + idDeanery , Department.class).getResultList();
                break;
        }
        System.out.println(department);
    }
}