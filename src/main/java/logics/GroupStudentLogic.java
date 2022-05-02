package logics;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import models.*;
import java.util.List;
public class GroupStudentLogic {
    public void work(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        List<Student> student = session.createQuery("SELECT ex FROM Student ex", Student.class).getResultList();
        System.out.println("Группа\t\t\tФамилия студента\t\tИмя студента\t\t Отчество студента");
        for (int i = 0; i < student.size(); i++) {
            System.out.format("%s \t\t  %s \t %s\t\t%s %n", student.get(i).getGroup_class().getGroupName(), student.get(i).getStudentSurname(), student.get(i).getStudentName(), student.get(i).getStudentMiddlename());
        }
        session.getTransaction().commit();
    }
}