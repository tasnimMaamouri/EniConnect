package tn.enicarthage.EniConnect.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Entity
@Table(name = "T_Post")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Post implements Serializable {

    @Id
    @Column(name="Identifier")
    private Long ID;

    @Column(name="ownerID")
    private String ownerID;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;

    @Column(name="status")
    private Status status ;

    @ManyToOne
    private AncienEtudiant etd;

    @ManyToOne
    private Admin admin;


    public void setContent(byte[] pdfBytes) {
    }
}
