package models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student", schema = "public", catalog = "lab4")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column(name = "student_surname")
    private String studentSurname;
    @Column(name = "student_name")
    private String studentName;
    @Column(name = "student_middlename")
    private String studentMiddlename;
    private Date birthdate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group_Class group;
    @OneToMany(mappedBy = "subgrade_sheet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subgrade_Sheet> subgrade_sheet;
    public Student(String studentSurname, String studentName, String studentMiddlename, Date birthdate, Group_Class group){
        this.studentSurname=studentSurname;
        this.studentName=studentName;
        this.studentMiddlename=studentMiddlename;
        this.birthdate=birthdate;
        this.group=group;
        subgrade_sheet=new ArrayList<>();
    }
    @Override
    public String toString() {
        return String.format("\nid: %d || фамилия: %s || имя: %s || отчество: %s || дата рождения: %s || группа: %s ",
                id, studentSurname, studentName, studentMiddlename, birthdate, group);
    }
}