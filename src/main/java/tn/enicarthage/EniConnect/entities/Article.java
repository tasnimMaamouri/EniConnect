package tn.enicarthage.EniConnect.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="Article")
public class Article {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idArticle")
    private Long id;
    @Column(name="titleArticle")
    private String title;

    @Lob
    @Column(columnDefinition = "LONGTEXT",name="contentArticle")
    private String content;
    @Column(name="videoUrl")
    private String videoUrl;


    @ManyToOne()
    @JoinColumn(name = "AncienEtudiant",referencedColumnName = "idAncienEtudiant")
    private AncienEtudiant ancienEtudiant;
}
