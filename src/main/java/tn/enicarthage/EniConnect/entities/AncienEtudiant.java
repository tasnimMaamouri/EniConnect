package tn.enicarthage.EniConnect.entities;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="AncienEtudiant",uniqueConstraints={ @UniqueConstraint(columnNames = "emailAncienEtudiant")})
public class AncienEtudiant {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idAncienEtudiant")
    private Long id;

    @Column(name="nomPrenomAncienEtudiant")
    private String nomPrenom;

    @Column(name="phoneAncienEtudiant")
    private String phone;

    @Column(name="emailAncienEtudiant")
    private String emailInstitutionnelle;

    @Column(name="emailPersonnelleAncienEtudiant")
    private String emailPersonnelle;

    @Column(name="nomDeSociete")
    private String nomDeSociete;

    @Column(name="premiereSalaire")
    private float  premiereSalaire;

    @Column(name="salaireActuelle")
    private float salaireActuelle;

    @Column(name="specialiteAncienEtudiant")
    private Specialite specialite;

    @Column(name="promotionAncienEtudiant")
    private int promotion;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "ancienEtudiant")
    private List<Article> articles;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "ancienEtudiant")
    private List<Post> posts;


}
