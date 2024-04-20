package ENIConnect.projetEnicar.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Setter
@NoArgsConstructor
@ToString
@Entity
public class NouveauEtudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idNouveauEtudiant")
    private Long id;

    @Column(name = "nomNouveauEtudiant")
    private String nom;
    @Column(name = "prenomNouveauEtudiant")
    private String prenom;
    @Column(name = "dateNaissanceNouveauEtudiant")
    private LocalDate dateNaiss;
    @Column(name = "adresseNouveauEtudiant")
    private String adresse;
    @Column(name = "SpecialiteNouveauEtudiant")
    private String specialite;
    @Column(name = "emailPersonnelNouveauEtudiant")
    private String email_Personnel;
    @Column(name = "emailInstitutionnelNouveauEtudiant")
    private String email_Institutionnel;
    //@Fetch(FetchMode.SUBSELECT)
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Lob
    @Column(columnDefinition = "LONGBLOB",name = "cvNouveauEtudiant")
    private byte[] cv;
    @OneToMany(mappedBy = "nouveauEtudiant")
    @JsonIgnore
    private List<Candidature> candidatures = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public List<Candidature> getCandidatures() {
        return candidatures;
    }

    public byte[] getCv() {
        return cv;
    }

    public void setCv(byte[] cv) {
        this.cv = cv;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(LocalDate dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getEmail_Personnel() {
        return email_Personnel;
    }

    public void setEmail_Personnel(String email_Personnel) {
        this.email_Personnel = email_Personnel;
    }

    public String getEmail_Institutionnel() {
        return email_Institutionnel;
    }

    public void setEmail_Institutionnel(String email_Institutionnel) {
        this.email_Institutionnel = email_Institutionnel;
    }

    public NouveauEtudiant(String nom, String prenom, LocalDate dateNaiss, String adresse, String specialite, String email_Personnel, String email_Institutionnel) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaiss = dateNaiss;
        this.adresse = adresse;
        this.specialite = specialite;
        this.email_Personnel = email_Personnel;
        this.email_Institutionnel = email_Institutionnel;
        this.cv = cv;
    }
}
