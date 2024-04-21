package tn.enicarthage.EniConnect.entities;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="Utilisateur",uniqueConstraints={ @UniqueConstraint(columnNames = "emailUtilisateur")})
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUtilisateur")
    private Long id;
    @Column(name = "emailUtilisateur")
    private String email;
    @Column(name="motDePasseUtilisateur")
    private String motDePasse;

    @JoinTable
            (name = "utilisateur_role",
                    joinColumns = @JoinColumn(name = "idUtilisateur"),
                    inverseJoinColumns = @JoinColumn(name = "idRole"))
    @ManyToMany()
    Set<Role> roles;
    public Utilisateur(String email, String motDePasse) {
        this.email = email;
        this.motDePasse = motDePasse;

    }




}
