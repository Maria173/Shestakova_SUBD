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
@Table(name = "group_class", schema = "public", catalog = "lab4")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Group_Class {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column(name = "group_name")
    private String groupName;
    @Column(name = "admission_year")
    private Date admissionYear;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flow_id")
    private Flow flow;
    @OneToMany(mappedBy = "group_class", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Grade_Sheet> grade_sheet;
    @OneToMany(mappedBy = "group_class", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> student;
    public Group_Class(String groupName, Date admissionYear, Flow flow){
        this.groupName=groupName;
        this.admissionYear=admissionYear;
        this.flow=flow;
        grade_sheet=new ArrayList<>();
        student=new ArrayList<>();
    }
    @Override
    public String toString() {
        return String.format("\nid: %d || группа: %s || год: %s поток: %s ",
                id, groupName, admissionYear, flow);
    }
}