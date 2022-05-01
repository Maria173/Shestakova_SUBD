package logics;
import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;
public class SubgradeSheetLogic {
    public void work(SessionFactory sessionFactory) {
        System.out.println("Введите 1 для создания подведомости");
        System.out.println("Введите 2 для чтения подведомости");
        System.out.println("Введите 3 для изменения подведомости");
        System.out.println("Введите 4 для удаления подведомости");
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
        System.out.println("Введите оценку");
        int grade = scanner.nextInt();
        System.out.println("Введите номер ведомости");
        int idGradeSheet = scanner.nextInt();
        System.out.println("Введите номер студента");
        int idStudent = scanner.nextInt();

        Subgrade_Sheet subgrade_sheet = new Subgrade_Sheet(grade, session.get(Grade_Sheet.class,idGradeSheet), session.get(Student.class,idStudent));
        session.save(subgrade_sheet);
    }

    private void read(Session session) {
        List<Subgrade_Sheet> subgrade_sheet = session.createQuery("SELECT a from Subgrade_Sheet a", Subgrade_Sheet.class).getResultList();
        System.out.println(subgrade_sheet);
    }

    private void update(Session session) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите id подведомости");
        int id = scanner.nextInt();

        System.out.println("Введите оценку");
        int grade = scanner.nextInt();
        System.out.println("Введите номер ведомости");
        int idGradeSheet = scanner.nextInt();
        System.out.println("Введите номер студента");
        int idStudent = scanner.nextInt();

        Subgrade_Sheet subgrade_sheet = session.get(Subgrade_Sheet.class, id);
        subgrade_sheet.setGrade(grade);
        subgrade_sheet.setGrade_sheet(session.get(Grade_Sheet.class, idGradeSheet));
        subgrade_sheet.setStudent(session.get(Student.class, idStudent));
        session.save(subgrade_sheet);
    }

    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id подведомости");
        int id = scanner.nextInt();

        Subgrade_Sheet subgrade_sheet = session.get(Subgrade_Sheet.class, id);
        session.delete(subgrade_sheet);
    }

    private void filterRead(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите 1 для фильтрации по году");
        System.out.println("Введите 2 для фильтрации по семестру");
        System.out.println("Введите 3 для фильтрации по номеру группы");
        System.out.println("Введите 4 для фильтрации по номеру преподавателя");
        System.out.println("Введите 5 для фильтрации по номеру предмета");
        int numb = scanner.nextInt();
        List<Subgrade_Sheet> subgrade_sheet = null;
        switch(numb) {
            case 1:
                System.out.println("Введите оценку");
                int grade = scanner.nextInt();
                subgrade_sheet = session.createQuery("SELECT a from Subgrade_Sheet a where grade = \'" + grade + "\'", Subgrade_Sheet.class).getResultList();
                break;
            case 2:
                System.out.println("Введите номер ведомости");
                int idGradeSheet = scanner.nextInt();
                subgrade_sheet = session.createQuery("SELECT a from Subgrade_Sheet a where grade_sheet = " + idGradeSheet , Subgrade_Sheet.class).getResultList();
                break;
            case 3:
                System.out.println("Введите номер студента");
                int idStudent = scanner.nextInt();
                subgrade_sheet = session.createQuery("SELECT a from Subgrade_Sheet a where student = " + idStudent , Subgrade_Sheet.class).getResultList();
                break;
        }
        System.out.println(subgrade_sheet);
    }
}