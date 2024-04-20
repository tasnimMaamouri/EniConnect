package tn.enicarthage.EniConnect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.EniConnect.entities.Candidature;
import tn.enicarthage.EniConnect.services.CandidatureService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/candidatures")
public class CandidatureController {
    @Autowired
    private CandidatureService cdService;
    @RequestMapping(method = RequestMethod.GET)
    public List<Candidature> getAllCandidatures(){
        return cdService.getAllCandidatures();
    }
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public Candidature getCandidatureById(@PathVariable("id") Long id){
        return cdService.getCAndidature(id);
    }
    @RequestMapping(method = RequestMethod.POST)
    public Candidature createNouvelleCandidature(@RequestBody Candidature cd){
        cd.setDateSoumission(LocalDate.now());
        return cdService.saveNouvelleCandidature(cd);
    }
    @RequestMapping(value = "/updateCandidature",method = RequestMethod.PUT)
    public Candidature updateCandidature(@RequestBody Candidature cd){
        return cdService.updateCandidature(cd);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void deleteCandidature(@PathVariable("id") Long id){

        cdService.deleteCandidatureById(id);
    }

}
