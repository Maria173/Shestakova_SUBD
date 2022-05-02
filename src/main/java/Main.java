import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import logics.*;
import models.*;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Deanery.class)
                .addAnnotatedClass(Department.class)
                .addAnnotatedClass(Flow.class)
                .addAnnotatedClass(Group_Class.class)
                .addAnnotatedClass(Teacher.class)
                .addAnnotatedClass(Subject.class)
                .addAnnotatedClass(Subject_Teacher_Assignment.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Grade_Sheet.class)
                .addAnnotatedClass(Subgrade_Sheet.class)
                .buildSessionFactory();

        boolean isWork = true;
        while(isWork){
            System.out.println("Введите 1 для работы с деканатами");
            System.out.println("Введите 2 для работы с кафедрами");
            System.out.println("Введите 3 для работы с потоками");
            System.out.println("Введите 4 для работы с группами");
            System.out.println("Введите 5 для работы со студентами");
            System.out.println("Введите 6 для работы с преподавателями");
            System.out.println("Введите 7 для работы с предметами");
            System.out.println("Введите 8 для работы с распределением");
            System.out.println("Введите 9 для работы с ведомостями");
            System.out.println("Введите 10 для работы с подведомостями");
            System.out.println("Введите 11 для вывода студентов и их групп");
            System.out.println("Введите 12 для выхода");

            Scanner scanner = new Scanner(System.in);
            int numb = scanner.nextInt();

            switch (numb){
                case 1:
                    DeaneryLogic deaneryLogic = new DeaneryLogic();
                    deaneryLogic.work(sessionFactory);
                    break;
                case 2:
                    DepartmentLogic departmentLogic = new DepartmentLogic();
                    departmentLogic.work(sessionFactory);
                    break;
                case 3:
                    FlowLogic flowLogic = new FlowLogic();
                    flowLogic.work(sessionFactory);
                    break;
                case 4:
                    GroupLogic groupLogic = new GroupLogic();
                    groupLogic.work(sessionFactory);
                    break;
                case 5:
                    StudentLogic studentLogic = new StudentLogic();
                    studentLogic.work(sessionFactory);
                    break;
                case 6:
                    TeacherLogic teacherLogic = new TeacherLogic();
                    teacherLogic.work(sessionFactory);
                    break;
                case 7:
                    SubjectLogic subjectLogic = new SubjectLogic();
                    subjectLogic.work(sessionFactory);
                    break;
                case 8:
                    AssignmentLogic assignmentLogic = new AssignmentLogic();
                    assignmentLogic.work(sessionFactory);
                    break;
                case 9:
                    GradeSheetLogic gradeSheetLogic = new GradeSheetLogic();
                    gradeSheetLogic.work(sessionFactory);
                    break;
                case 10:
                    SubgradeSheetLogic subgradeSheetLogic = new SubgradeSheetLogic();
                    subgradeSheetLogic.work(sessionFactory);
                    break;
                case 11:
                    GroupStudentLogic groupStudentLogic = new GroupStudentLogic();
                    groupStudentLogic.work(sessionFactory);
                    break;
                case 12:
                    isWork = false;
                    break;
            }
        }
        sessionFactory.close();
    }
}