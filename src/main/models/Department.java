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
@Table(name = "department", schema = "public", catalog = "lab4")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Department {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deanery_id")
    private Deanery deanery;
    @Column(name = "department_name")
    private String departmentName;
    private int phone;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Flow> flow;
    @OneToMany(mappedBy = "assignment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subject_Teacher_Assignment> assignment;
    public Department(Deanery deanery, String departmentName, int phone){
        this.deanery=deanery;
        this.departmentName=departmentName;
        this.phone=phone;
        flow=new ArrayList<>();
        assignment=new ArrayList<>();
    }
    @Override
    public String toString() {
        return String.format("\nid: %d || кафедра: %s || номер телефона: %s || деканат: %s ",
                id, departmentName, phone, deanery);
    }
}