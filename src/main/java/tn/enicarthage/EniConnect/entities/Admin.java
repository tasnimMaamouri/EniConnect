package tn.enicarthage.EniConnect.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;

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
//    @NotEmpty(message = "Email is required.")
//    @Email(message = "Valid email is required.")
    private  String email;
    @Column
//    @NotEmpty(message = "Password is required.")
//    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    public Integer getAge(){
        return Period.between(dateOfBirth,LocalDate.now()).getYears();
    }
}
