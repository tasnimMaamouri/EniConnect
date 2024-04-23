package tn.enicarthage.EniConnect.entities;

import javax.persistence.*;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)

@Table
public class Offre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_Offre")
    private Long id;

    @Column(name="title_Offre")
    private String title;

    @Lob
    @Column(columnDefinition = "LONGTEXT",name="Description_Offre")
    private String Description;

    @Column(columnDefinition = "LONGTEXT",name="Missions_Offre")
    private String missions ;

    @Column(name="Lieu_Offre ")
    private String lieu  ;
    @Column(name="Societe_Offre")
    private String societe ;
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    public Instant createdDate;
    @ManyToOne()
    @JoinColumn(name = "AncienEtudiant",referencedColumnName = "idAncienEtudiant")
    private AncienEtudiant ancienEtudiant;
}
