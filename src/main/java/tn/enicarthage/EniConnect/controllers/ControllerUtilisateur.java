package tn.enicarthage.EniConnect.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.EniConnect.entities.Utilisateur;
import tn.enicarthage.EniConnect.repositories.UtilisateurRepository;
import tn.enicarthage.EniConnect.services.IUtilisateur;

import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/GUtilisateur")

public class ControllerUtilisateur {
    @Autowired
    IUtilisateur utilisateurService;
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UtilisateurRepository utilisateurRepository;
    @GetMapping("/UtilisateurByEmail/{emailUtilisateur}")

    public ResponseEntity<Utilisateur> getUtilisateurByEmail(@PathVariable("emailUtilisateur") String emailUtilisateur ) {
        try {
             Utilisateur resultat=utilisateurService.getUtilisateurByEmail(emailUtilisateur);
            if (resultat!=null) {
                return new ResponseEntity<>(resultat, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/UpdateUtilisateur/{idUtilisateur}/{ancienneMotDePasse}")
    public ResponseEntity<Utilisateur> updateUtilisateur( @PathVariable ("idUtilisateur")Long idUtilisateur, @PathVariable ("ancienneMotDePasse")String ancienneMotDePasse,@RequestBody Utilisateur utilisateur) {
        try {
            Optional<Utilisateur> resultOptional = utilisateurRepository.findById(idUtilisateur);
            if (resultOptional.isPresent()) {
                Utilisateur utilisateur1=utilisateurRepository.findByEmailAndIdNot(utilisateur.getEmail(),idUtilisateur);
               if(  encoder.matches(ancienneMotDePasse,resultOptional.get().getMotDePasse())==false){
                   return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
               } else if(utilisateur1!=null){
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
                }else {
                   Utilisateur utilisateurresultat= utilisateurService.updateUtilisateur(utilisateur, idUtilisateur);
                    return new ResponseEntity<>(utilisateurresultat, HttpStatus.OK);
                }}else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PutMapping("/RefreshMotDePasse/{idUtilisateur}")
    public ResponseEntity<Utilisateur> updateUtilisateur(@RequestBody Utilisateur utilisateur, @PathVariable ("idUtilisateur")Long idUtilisateur) {
        try {
            Optional<Utilisateur> resultOptional = utilisateurRepository.findById(idUtilisateur);
            if (resultOptional.isPresent()) {
                Utilisateur utilisateur1=utilisateurRepository.findByEmailAndIdNot(utilisateur.getEmail(),idUtilisateur);
            if(utilisateur1!=null){
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
                }else {
                    Utilisateur utilisateurresultat= utilisateurService.updateUtilisateur(utilisateur, idUtilisateur);
                    return new ResponseEntity<>(utilisateurresultat, HttpStatus.OK);
                }}else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
