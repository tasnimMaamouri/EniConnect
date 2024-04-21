package tn.enicarthage.EniConnect.services;

import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.enicarthage.EniConnect.entities.Candidature;
import tn.enicarthage.EniConnect.entities.NouveauEtudiant;
import tn.enicarthage.EniConnect.repositories.CandidatureRepository;
import tn.enicarthage.EniConnect.repositories.NouveauEtudiantRepository;

import java.util.List;
import java.util.Optional;

@Service
public class INouveauEtudiantServiceImpl implements INouveauEtudiantService {
    @Autowired
    private NouveauEtudiantRepository etdrepo;
    @Autowired
    private CandidatureRepository cdrepo;
    @Override
    public void AffecterCandidatureAEtudiant(Long IdC, Long IdE) {
        NouveauEtudiant etdEntity =etdrepo.findById(IdE).get();
        Candidature cdEntity = cdrepo.findById(IdC).get();
        cdEntity.setNouveauEtudiant(etdEntity);
        cdrepo.save(cdEntity);
    }

    @Override
    public byte[] getCVByEtudiantId(Long etudiantId) {

       NouveauEtudiant etd = etdrepo.findById(etudiantId).orElse(null);
        if (etd != null && etd.getCv() != null) {
            return etd.getCv();
        }

        // Otherwise, return null or throw an exception based on your requirements
        return null;
    }

    @Override
    public NouveauEtudiant createNouveauEtudiant(NouveauEtudiant Etd) {
        return etdrepo.save(Etd);
    }

    @Override
    public NouveauEtudiant getEtudiantById(Long etudiantId) {
        // Utilisez votre repository ou tout autre mécanisme pour récupérer l'étudiant en fonction de son ID
        Optional<NouveauEtudiant> etudiantOptional = etdrepo.findById(etudiantId);

        // Vérifiez si l'étudiant existe
        if (etudiantOptional.isPresent()) {
            // Si l'étudiant existe, retournez-le
            return etudiantOptional.get();
        } else {
            // Si l'étudiant n'existe pas, vous pouvez choisir de retourner null ou de lever une exception
            // Dans cet exemple, nous choisissons de retourner null
            return null;
        }
    }

    @Override
    public void updateEtudiant(NouveauEtudiant nouvelEtudiant, Long etudiantId) {
        // 1. Récupérer l'étudiant existant
        NouveauEtudiant etudiantExistant = etdrepo.findById(etudiantId)
                .orElseThrow(() -> new EntityNotFoundException("Étudiant non trouvé avec l'ID : " + etudiantId));

        // 2. Mettre à jour les propriétés de l'étudiant existant avec les valeurs du nouvel étudiant
        etudiantExistant.setNom(nouvelEtudiant.getNom());
        etudiantExistant.setPrenom(nouvelEtudiant.getPrenom());
        etudiantExistant.setDateNaiss(nouvelEtudiant.getDateNaiss());
        etudiantExistant.setAdresse(nouvelEtudiant.getAdresse());
        etudiantExistant.setSpecialite(nouvelEtudiant.getSpecialite());
        etudiantExistant.setEmail_Personnel(nouvelEtudiant.getEmail_Personnel());
        etudiantExistant.setEmail_Institutionnel(nouvelEtudiant.getEmail_Institutionnel());
        // Mettre à jour d'autres propriétés selon vos besoins

        // 3. Enregistrer les modifications dans la base de données
        etdrepo.save(etudiantExistant);
    }

    @Override
    public List<NouveauEtudiant> getAllEtudiants() {
        try {
            return etdrepo.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public NouveauEtudiant getNouveauEtudiant(Long id) {
        Optional<NouveauEtudiant> optionalNouveauEtudiant = etdrepo.findById(id);
        return optionalNouveauEtudiant.orElse(null);
    }

    @Override
    public NouveauEtudiant saveNouveauEtudiant(NouveauEtudiant etd) {
        return etdrepo.save(etd);
    }

    @Override
    public NouveauEtudiant updateNouveauEtudiant(NouveauEtudiant etd) {
        NouveauEtudiant existingEtudiant = etdrepo.findById(etd.getId()).orElse(null);
        if (existingEtudiant == null) {
            return null;
        }
        existingEtudiant.setNom(etd.getNom());
        existingEtudiant.setPrenom(etd.getPrenom());
        existingEtudiant.setDateNaiss(etd.getDateNaiss());
        existingEtudiant.setAdresse(etd.getAdresse());
        existingEtudiant.setSpecialite(etd.getSpecialite());
        existingEtudiant.setEmail_Personnel(etd.getEmail_Personnel());
        existingEtudiant.setEmail_Institutionnel(etd.getEmail_Institutionnel());
        existingEtudiant.setCv(etd.getCv());
        return etdrepo.save(existingEtudiant);
    }

    @Override
    public void deleteNouveauEtudiantById(Long id) {
            etdrepo.deleteById(id);
        }



}
