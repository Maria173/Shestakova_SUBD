package models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "teacher", schema = "public", catalog = "lab4")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Teacher {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column(name = "teacher_surname")
    private String teacherSurname;
    @Column(name = "teacher_name")
    private String teacherName;
    @Column(name = "teacher_middlename")
    private String teacherMiddlename;
    private String post;
    private int experience;
    @OneToMany(mappedBy = "assignment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subject_Teacher_Assignment> assignment;
    @OneToMany(mappedBy = "grade_sheet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Grade_Sheet> grade_sheet;
    public Teacher(String teacherSurname, String teacherName, String teacherMiddlename, String post, int experience){
        this.teacherSurname=teacherSurname;
        this.teacherName=teacherName;
        this.teacherMiddlename=teacherMiddlename;
        this.post=post;
        this.experience=experience;
        assignment=new ArrayList<>();
        grade_sheet=new ArrayList<>();
    }
    @Override
    public String toString() {
        return String.format("\nid: %d || фамилия: %s || имя: %s || отчество: %s || должность: %s || опыт: %s ",
                id, teacherSurname, teacherName, teacherMiddlename, post, experience);
    }
}