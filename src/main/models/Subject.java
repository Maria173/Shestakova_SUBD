package models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "subject", schema = "public", catalog = "lab4")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Subject {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column(name = "subject_name")
    private String subjectName;
    private int hours;
    @OneToMany(mappedBy = "assignment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subject_Teacher_Assignment> assignment;
    public Subject(String subjectName, int hours){
        this.subjectName=subjectName;
        this.hours=hours;
        assignment=new ArrayList<>();
    }
    @Override
    public String toString() {
        return String.format("\nid: %d || название: %s || часы: %s ",
                id, subjectName, hours);
    }
}