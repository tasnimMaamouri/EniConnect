package tn.enicarthage.EniConnect.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table
public class Post {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Long Id;

    @Column(name="title")
    private String title;

    @Lob
    @Column(name="content")
    private String content;

    @Column(name="status")
    @Enumerated(EnumType.STRING) // Specify that the status is stored as a string
    private Status status = Status.DRAFT; // Default status set to DRAFT


    @ManyToOne()
    @JoinColumn(name = "AncienEtudiant",referencedColumnName = "idAncienEtudiant")
    private AncienEtudiant ancienEtudiant;
}
