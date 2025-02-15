package tn.enicarthage.EniConnect.entities;

import lombok.*;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adminId", unique = true)
    private Long id;

    @Column
    private String firstname;
    @Column
    private String lastname;
    @Column
    private LocalDate dateOfBirth;
    @Column
    private  String post;
    @Transient
    private float age;
    @Column
    private  String email;

    public Integer getAge(){
        return Period.between(dateOfBirth,LocalDate.now()).getYears();
    }



}
