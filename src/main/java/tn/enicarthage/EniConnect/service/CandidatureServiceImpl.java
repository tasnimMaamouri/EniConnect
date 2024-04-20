package tn.enicarthage.EniConnect.service;

import tn.enicarthage.EniConnect.entities.Candidature;
import tn.enicarthage.EniConnect.repository.CandidatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidatureServiceImpl implements CandidatureService{
    @Autowired
    private CandidatureRepository cdrepo;

    @Override
    public Candidature createNouvelleCandidature(Candidature candidature) {
        return cdrepo.save(candidature);
    }

    @Override
    public List<Candidature> getAllCandidatures() {
        try {
            return cdrepo.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Candidature getCAndidature(Long id) {
        Optional<Candidature> optionalCandidature = cdrepo.findById(id);
        return optionalCandidature.orElse(null);
    }

    @Override
    public Candidature saveNouvelleCandidature(Candidature cd) {
        return cdrepo.save(cd);
    }

    @Override
    public Candidature updateCandidature(Candidature cd) {
        Candidature existingCAndidature =cdrepo.findById(cd.getIdC()).orElse(null);
        if (existingCAndidature == null) {
            return null;
        }
        //existingCAndidature.setDateSoumission(cd.getDateSoumission());
        existingCAndidature.setEtat(cd.getEtat());
        return cdrepo.save(existingCAndidature);
    }

    @Override
    public void deleteCandidatureById(Long id) {
        cdrepo.deleteById(id);
    }

}
