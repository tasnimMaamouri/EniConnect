package tn.enicarthage.EniConnect.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.enicarthage.EniConnect.Entities.AncienEtudiant;

import java.util.Optional;

public interface AncienEtudiantRepo extends JpaRepository<AncienEtudiant,Long> {
    static Optional<AncienEtudiant> findByEmailPersonnel(String email) {

        return null;
    }
    }
