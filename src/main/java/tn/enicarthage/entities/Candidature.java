package tn.enicarthage.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
public class Candidature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idC;
    private LocalDate dateSoumission;



    @Enumerated(EnumType.STRING)
    private Etat etat;
    @ManyToOne
    @JoinColumn(name = "idEtudiant")
    private NouveauEtudiant nouveauEtudiant;
    public Long getIdC() {
        return idC;
    }

    public LocalDate getDateSoumission() {
        return dateSoumission;
    }

    public void setDateSoumission(LocalDate dateSoumission) {
        this.dateSoumission = dateSoumission;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setNouveauEtudiant(NouveauEtudiant nouveauEtudiant) {
        this.nouveauEtudiant = nouveauEtudiant;
    }

    public NouveauEtudiant getNouveauEtudiant() {
        return nouveauEtudiant;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Candidature(LocalDate dateSoumission, Etat etat) {
        this.dateSoumission = dateSoumission;
        this.etat = etat;
    }
}
