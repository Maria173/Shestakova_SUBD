package models;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

@Entity
@Table(name = "flow", schema = "public", catalog = "lab4")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Flow {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;
    @Column(name = "flow_name")
    private String flowName;
    @OneToMany(mappedBy = "flow", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Group_Class> group_class;
    public Flow(String flowName, Department department){
        this.department=department;
        this.flowName=flowName;
        group_class=new ArrayList<>();
    }
    @Override
    public String toString() {
        return String.format("\nid: %d || поток: %s || кафедра: %s ",
                id, flowName, department);
    }
}