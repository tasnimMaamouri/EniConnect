package tn.enicarthage.EniConnect.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.enicarthage.EniConnect.entities.Utilisateur;
import tn.enicarthage.EniConnect.repositories.UtilisateurRepository;

import java.util.Optional;

@Service

public class UtilisateurImpl implements IUtilisateur {
    @Autowired
    UtilisateurRepository utilisateurR;
    @Autowired
    PasswordEncoder encoder;
    @Override
    public Utilisateur getUtilisateurByEmail(String email) {
        Optional<Utilisateur> resultat=utilisateurR.findByEmail(email);
        if(resultat.isPresent()){
            return resultat.get();
        }else {
            return null;
        }
    }
    @Override
    public Utilisateur updateUtilisateur(Utilisateur utilisateur, Long id) {
        Optional<Utilisateur> resultOptional = utilisateurR.findById(id);
if(resultOptional.isPresent()){
    if(utilisateur.getEmail()!=null) {
        resultOptional.get().setEmail(utilisateur.getEmail());



    }
    if(utilisateur.getMotDePasse()!=null){

           resultOptional.get().setMotDePasse(encoder.encode(utilisateur.getMotDePasse()));



    }
    utilisateurR.save(resultOptional.get());

}
return resultOptional.get();
    }
}
