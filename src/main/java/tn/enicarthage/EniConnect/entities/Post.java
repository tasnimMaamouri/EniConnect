package tn.enicarthage.EniConnect.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Entity
@Table(name = "Post")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Post implements Serializable {

    @Id
    @Column(name="Identifier")
    private Long ID;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;

    @Column(name="status")
    private Status status ;

    @ManyToOne
    @JoinColumn(name = "AncienEtudiant",referencedColumnName = "idAncienEtudiant")
    private AncienEtudiant ancienEtudiant;


}
