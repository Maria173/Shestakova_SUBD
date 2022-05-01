package models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "deanery", schema = "public", catalog = "lab4")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Deanery {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column(name = "deanery_name")
    private String deaneryName;
    @Column(name = "dean_surname")
    private String deanSurname;
    @Column(name = "dean_name")
    private String deanName;
    @Column(name = "dean_middlename")
    private String deanMiddlename;
    private int phone;
    @OneToMany(mappedBy = "deanery", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Department> department;
    public Deanery(String deaneryName, String deanSurname, String deanName, String deanMiddlename, int phone){
        this.deaneryName=deaneryName;
        this.deanSurname=deanSurname;
        this.deanName=deanName;
        this.deanMiddlename=deanMiddlename;
        this.phone=phone;
        department=new ArrayList<>();
    }
    @Override
    public String toString() {
        return String.format("\nid: %d || название: %s || фамилия: %s || имя: %s || отчество: %s || номер телефона: %s ",
                id, deaneryName, deanSurname, deanName, deanMiddlename, phone);
    }
}