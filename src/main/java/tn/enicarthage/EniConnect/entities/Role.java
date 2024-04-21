package tn.enicarthage.EniConnect.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="Role",uniqueConstraints={ @UniqueConstraint(columnNames = "nomRole")})
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRole")
    private Long id;
    @Column(name="nomRole")
    private String nom;


}
