package logics;
import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;
public class GradeSheetLogic {
    public void work(SessionFactory sessionFactory) {
        System.out.println("Введите 1 для создания ведомости");
        System.out.println("Введите 2 для чтения ведомости");
        System.out.println("Введите 3 для изменения ведомости");
        System.out.println("Введите 4 для удаления ведомости");
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
        System.out.println("Введите год");
        String year = scanner.next();
        java.util.Date myDate = new java.util.Date(year);
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        System.out.println("Введите семестр");
        int semester = scanner.nextInt();
        System.out.println("Введите номер группы");
        int idGroup = scanner.nextInt();
        System.out.println("Введите номер преподавателя");
        int idTeacher = scanner.nextInt();
        System.out.println("Введите номер предмета");
        int idSubject = scanner.nextInt();

        Grade_Sheet grade_sheet = new Grade_Sheet(sqlDate, semester, session.get(Group_Class.class,idGroup), session.get(Teacher.class,idTeacher), session.get(Subject.class,idSubject));
        session.save(grade_sheet);
    }

    private void read(Session session) {
        List<Grade_Sheet> grade_sheet = session.createQuery("SELECT a from Grade_Sheet a", Grade_Sheet.class).getResultList();
        System.out.println(grade_sheet);
    }

    private void update(Session session) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите id ведомости");
        int id = scanner.nextInt();

        System.out.println("Введите год");
        String year = scanner.next();
        java.util.Date myDate = new java.util.Date(year);
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        System.out.println("Введите семестр");
        int semester = scanner.nextInt();
        System.out.println("Введите номер группы");
        int idGroup = scanner.nextInt();
        System.out.println("Введите номер преподавателя");
        int idTeacher = scanner.nextInt();
        System.out.println("Введите номер предмета");
        int idSubject = scanner.nextInt();

        Grade_Sheet grade_sheet = session.get(Grade_Sheet.class, id);
        grade_sheet.setYear(sqlDate);
        grade_sheet.setSemester(semester);
        grade_sheet.setGroup(session.get(Group_Class.class, idGroup));
        grade_sheet.setTeacher(session.get(Teacher.class, idTeacher));
        grade_sheet.setSubject(session.get(Subject.class, idSubject));
        session.save(grade_sheet);
    }

    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id ведомости");
        int id = scanner.nextInt();

        Grade_Sheet grade_sheet = session.get(Grade_Sheet.class, id);
        session.delete(grade_sheet);
    }

    private void filterRead(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите 1 для фильтрации по году");
        System.out.println("Введите 2 для фильтрации по семестру");
        System.out.println("Введите 3 для фильтрации по номеру группы");
        System.out.println("Введите 4 для фильтрации по номеру преподавателя");
        System.out.println("Введите 5 для фильтрации по номеру предмета");
        int numb = scanner.nextInt();
        List<Grade_Sheet> grade_sheet = null;
        switch(numb) {
            case 1:
                System.out.println("Введите год");
                String date = scanner.next();
                java.util.Date myDate = new java.util.Date(date);
                java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
                grade_sheet = session.createQuery("SELECT a from Grade_Sheet a where year = \'" + date + "\'", Grade_Sheet.class).getResultList();
                break;
            case 2:
                System.out.println("Введите семестр");
                int semester = scanner.nextInt();
                grade_sheet = session.createQuery("SELECT a from Grade_Sheet a where semester = \'" + semester + "\'", Grade_Sheet.class).getResultList();
                break;
            case 3:
                System.out.println("Введите номер группы");
                int idGroup = scanner.nextInt();
                grade_sheet = session.createQuery("SELECT a from Grade_Sheet a where group = " + idGroup , Grade_Sheet.class).getResultList();
                break;
            case 4:
                System.out.println("Введите номер преподавателя");
                int idTeacher = scanner.nextInt();
                grade_sheet = session.createQuery("SELECT a from Grade_Sheet a where teacher = " + idTeacher , Grade_Sheet.class).getResultList();
                break;
            case 5:
                System.out.println("Введите номер предмета");
                int idSubject = scanner.nextInt();
                grade_sheet = session.createQuery("SELECT a from Grade_Sheet a where subject = " + idSubject , Grade_Sheet.class).getResultList();
                break;
        }
        System.out.println(grade_sheet);
    }
}