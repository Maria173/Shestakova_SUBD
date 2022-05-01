package logics;
import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;
public class TeacherLogic {
    public void work(SessionFactory sessionFactory) {
        System.out.println("Введите 1 для создания преподавателя");
        System.out.println("Введите 2 для чтения преподавателя");
        System.out.println("Введите 3 для изменения преподавателя");
        System.out.println("Введите 4 для удаления преподавателя");
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
        System.out.println("Введите фамилию преподавателя");
        String teacherSurname = scanner.next();
        System.out.println("Введите имя преподавателя");
        String teacherName = scanner.next();
        System.out.println("Введите отчество преподавателя");
        String teacherMiddlename = scanner.next();
        System.out.println("Введите должность");
        String post = scanner.next();
        System.out.println("Введите опыт");
        int experience = scanner.nextInt();

        Teacher teacher = new Teacher(teacherSurname, teacherName, teacherMiddlename, post, experience);
        session.save(teacher);
    }

    private void read(Session session) {
        List<Teacher> teacher = session.createQuery("SELECT a from Teacher a", Teacher.class).getResultList();
        System.out.println(teacher);
    }

    private void update(Session session) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите id преподавателя");
        int id = scanner.nextInt();

        System.out.println("Введите фамилию преподавателя");
        String teacherSurname = scanner.next();
        System.out.println("Введите имя преподавателя");
        String teacherName = scanner.next();
        System.out.println("Введите отчество преподавателя");
        String teacherMiddlename = scanner.next();
        System.out.println("Введите должность");
        String post = scanner.next();
        System.out.println("Введите опыт");
        int experience = scanner.nextInt();

        Teacher teacher = session.get(Teacher.class, id);
        teacher.setTeacherSurname(teacherSurname);
        teacher.setTeacherName(teacherName);
        teacher.setTeacherMiddlename(teacherMiddlename);
        teacher.setPost(post);
        teacher.setExperience(experience);
        session.save(teacher);
    }

    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id преподавателя");
        int id = scanner.nextInt();

        Teacher teacher = session.get(Teacher.class, id);
        session.delete(teacher);
    }

    private void filterRead(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите 1 для фильтрации по фамилии");
        System.out.println("Введите 2 для фильтрации по имени");
        System.out.println("Введите 3 для фильтрации по отчеству");
        System.out.println("Введите 4 для фильтрации по должности");
        System.out.println("Введите 5 для фильтрации по опыту");
        int numb = scanner.nextInt();
        List<Teacher> teacher = null;
        switch(numb) {
            case 1:
                System.out.println("Введите фамилию");
                String teacherSurname = scanner.next();
                teacher = session.createQuery("SELECT a from Teacher a where teacherSurname = \'" + teacherSurname + "\'", Teacher.class).getResultList();
                break;
            case 2:
                System.out.println("Введите имя");
                String teacherName = scanner.next();
                teacher = session.createQuery("SELECT a from Teacher a where teacherName = \'" + teacherName + "\'", Teacher.class).getResultList();
                break;
            case 3:
                System.out.println("Введите отчество");
                String teacherMiddlename = scanner.next();
                teacher = session.createQuery("SELECT a from Teacher a where teacherMiddlename = \'" + teacherMiddlename + "\'", Teacher.class).getResultList();
                break;
            case 4:
                System.out.println("Введите должность");
                int post = scanner.nextInt();
                teacher = session.createQuery("SELECT a from Teacher a where post = \'" + post + "\'", Teacher.class).getResultList();
                break;
            case 5:
                System.out.println("Введите опыт");
                int experience = scanner.nextInt();
                teacher = session.createQuery("SELECT a from Teacher a where experience = \'" + experience + "\'", Teacher.class).getResultList();
                break;
        }
        System.out.println(teacher);
    }
}