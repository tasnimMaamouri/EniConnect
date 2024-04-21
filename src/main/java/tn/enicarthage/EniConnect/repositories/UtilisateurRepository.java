package tn.enicarthage.EniConnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.enicarthage.EniConnect.entities.Utilisateur;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByEmail(String email);

    Utilisateur findByEmailAndIdNot(String email, Long id);

    Boolean existsByEmail(String email);
}
