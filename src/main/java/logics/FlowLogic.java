package logics;
import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;
public class FlowLogic {
    public void work(SessionFactory sessionFactory) {
        System.out.println("Введите 1 для создания потока");
        System.out.println("Введите 2 для чтения потока");
        System.out.println("Введите 3 для изменения потока");
        System.out.println("Введите 4 для удаления потока");
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
        System.out.println("Введите название потока");
        String name = scanner.next();
        System.out.println("Введите номер кафедры");
        int idDepartment = scanner.nextInt();

        Flow flow = new Flow(name, session.get(Department.class,idDepartment));
        session.save(flow);
    }

    private void read(Session session) {
        List<Flow> flow = session.createQuery("SELECT a from Flow a", Flow.class).getResultList();
        System.out.println(flow);
    }

    private void update(Session session) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите id потока");
        int id = scanner.nextInt();

        System.out.println("Введите название потока");
        String name = scanner.next();
        System.out.println("Введите номер кафедры");
        int idDepartment = scanner.nextInt();

        Flow flow = session.get(Flow.class, id);
        flow.setFlowName(name);
        flow.setDepartment(session.get(Department.class, idDepartment));
        session.save(flow);
    }

    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id потока");
        int id = scanner.nextInt();

        Flow flow = session.get(Flow.class, id);
        session.delete(flow);
    }

    private void filterRead(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите 1 для фильтрации по названию потока");
        System.out.println("Введите 2 для фильтрации по номеру кафедры");
        int numb = scanner.nextInt();
        List<Flow> flow = null;
        switch(numb) {
            case 1:
                System.out.println("Введите название потока");
                String name = scanner.next();
                flow = session.createQuery("SELECT a from Flow a where flowName = \'" + name + "\'", Flow.class).getResultList();
                break;
            case 2:
                System.out.println("Введите номер кафедры");
                int idDepartment = scanner.nextInt();
                flow = session.createQuery("SELECT a from Flow a where department = " + idDepartment , Flow.class).getResultList();
                break;
        }
        System.out.println(flow);
    }
}