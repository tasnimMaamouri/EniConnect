package tn.enicarthage.EniConnect.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="T_AncienEtudiant",uniqueConstraints={ @UniqueConstraint(columnNames = "emailAncienEtudiant")})
public class AncienEtudiant implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idAncienEtudiant")
    private Long id;

    @Column(name="nomPrenom")
    private String nomPrenom;

    @Column(name="phone")
    private String phone;

    @Column(name="emailInstitutionnel")
    private String emailInstitutionnel;

    @Column(name="emailPersonnel")
    private String emailPersonnel;

    @Column(name="nomDeSociete")
    private String nomDeSociete;

    @Column(name="premierSalaire")
    private float  premiereSalaire;

    @Column(name="salaireActuel")
    private float salaireActuel;

    @Column(name="specialite")
    private Specialite specialite;

    @Column(name="promotion")
    private int promotion;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "etd")
    private List<Post> posts;


}
