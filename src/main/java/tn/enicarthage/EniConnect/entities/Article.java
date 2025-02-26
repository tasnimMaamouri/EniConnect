package tn.enicarthage.EniConnect.entities;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)

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

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    public Instant createdDate;

    @ManyToOne()
    @JoinColumn(name = "AncienEtudiant",referencedColumnName = "idAncienEtudiant")
    private AncienEtudiant ancienEtudiant;
}
