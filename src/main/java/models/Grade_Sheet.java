package models;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

@Entity
@Table(name = "grade_sheet", schema = "public", catalog = "lab4")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Grade_Sheet {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    private Date year;
    private int semester;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group_Class group;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;
    @OneToMany(mappedBy = "subgrade_sheet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subgrade_Sheet> subgrade_sheet;
    public Grade_Sheet(Date year, int semester, Group_Class group, Teacher teacher, Subject subject){
        this.year=year;
        this.semester=semester;
        this.group=group;
        this.teacher=teacher;
        this.subject=subject;
        subgrade_sheet=new ArrayList<>();
    }
    @Override
    public String toString() {
        return String.format("\nid: %d || год: %s || семестр: %s || группа: %s || преподаватель: %s || предмет: %s ",
                id, year, semester, group, teacher, subject);
    }
}