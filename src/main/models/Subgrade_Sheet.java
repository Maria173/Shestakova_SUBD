package models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "subgrade_sheet", schema = "public", catalog = "lab4")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Subgrade_Sheet {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private int grade;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grade_sheet_id")
    private Grade_Sheet grade_sheet;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;
    public Subgrade_Sheet(int grade, Grade_Sheet grade_sheet, Student student){
        this.grade=grade;
        this.grade_sheet=grade_sheet;
        this.student=student;
    }
    @Override
    public String toString() {
        return String.format("\nid: %d || ведомость: %s || студент: %s || оценка: %s ",
                id, grade_sheet, student, grade);
    }
}