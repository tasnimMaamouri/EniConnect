package tn.enicarthage.EniConnect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.EniConnect.entities.AncienEtudiant;
import tn.enicarthage.EniConnect.services.IAncienEtudiantService;

import java.util.List;

@Controller
@RequestMapping("/GAncienEtudiant")
public class AncienEtudiantController {
    @Autowired
    private IAncienEtudiantService ancienEtudiantService;

    @PostMapping("/AddAncienEtudiant")
    public ResponseEntity<AncienEtudiant> addAncienEtudiant(@RequestBody AncienEtudiant ancienEtudiant) {
        try {
            AncienEtudiant ancienEtudiant1 = ancienEtudiantService.getAncienEtudiantByEmail(ancienEtudiant.getEmailInstitutionnelle());
            if(ancienEtudiant1 !=null){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }else {
                return new  ResponseEntity<AncienEtudiant>(ancienEtudiantService.addAncienEtudiant(ancienEtudiant), HttpStatus.CREATED);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/AllAncienEtudiant")

    public ResponseEntity<List<AncienEtudiant>> getAllAncienEtudiant(){
        try {
            if(ancienEtudiantService.getAllAncienEtudiant().isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(ancienEtudiantService.getAllAncienEtudiant(), HttpStatus.OK) ;
            }
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/AncienEtudiant/{idAncienEtudiant}")

    public ResponseEntity<AncienEtudiant> getAncienEtudiantById(@PathVariable ("idAncienEtudiant") Long idAncienEtudiant) {
        try {
            AncienEtudiant resultat= ancienEtudiantService.getAncienEtudiantById(idAncienEtudiant);
            if (resultat!=null) {
                return new ResponseEntity<>(resultat, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PutMapping("/UpdateAncienEtudiant/{idAncienEtudiant}")

    public ResponseEntity<AncienEtudiant> updateAncienEtudiant(@RequestBody AncienEtudiant ancienEtudiant, @PathVariable ("idAncienEtudiant") Long idAncienEtudiant) {
        try {
            if (ancienEtudiantService.getAncienEtudiantById(idAncienEtudiant)!=null) {
                AncienEtudiant ancienEtudiantByemail= ancienEtudiantService.getAncienEtudiantByEmailAndNotId(ancienEtudiant.getEmailInstitutionnelle(),idAncienEtudiant);
                if(ancienEtudiantByemail!=null){
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
                }else
                {
                    return new ResponseEntity<>(ancienEtudiantService.updateAncienEtudiant(ancienEtudiant, idAncienEtudiant),HttpStatus.OK);
                }

            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/DeleteAncienEtudiant/{idAncienEtudiant}")

    public ResponseEntity<HttpStatus> deleteAncienEtudiant(@PathVariable ("idAncienEtudiant") Long idAncienEtudiant) {
        try {
            if (ancienEtudiantService.getAncienEtudiantById(idAncienEtudiant)!=null) {
                ancienEtudiantService.deleteAncienEtudiant(idAncienEtudiant);
                return new ResponseEntity<>( HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
