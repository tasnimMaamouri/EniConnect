package tn.enicarthage.EniConnect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.enicarthage.EniConnect.entities.NouveauEtudiant;
import tn.enicarthage.EniConnect.services.INouveauEtudiantService;

import java.util.List;

@RestController
public class NouveauEtudiantController {
    @Autowired
    private INouveauEtudiantService etdservice;
    @PutMapping("/AffecterCandidatureAEtudiant/{IdC}/{IdE}")
    @ResponseBody
    public void AffecterCandidatureAEtudiant(@PathVariable("IdC") Long IdC, @PathVariable("IdE") Long IdE){
        etdservice.AffecterCandidatureAEtudiant(IdC,IdE);
    }
    @PostMapping("/{etudiantId}/cv/upload")
    public ResponseEntity<String> uploadCV(@PathVariable Long etudiantId, @RequestParam("file") MultipartFile file) {
        try {
            NouveauEtudiant optionalEtudiant = etdservice.getEtudiantById(etudiantId);

            NouveauEtudiant a = optionalEtudiant;

            a.setCv(file.getBytes()); // Store the PDF file as byte array

          etdservice.updateEtudiant(a, etudiantId);

            return ResponseEntity.ok("CV uploaded successfully for Article with ID: " + etudiantId);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload CV!");
        }
    }
    @GetMapping("/{etudiantId}/cv/download")
    public ResponseEntity<byte[]> downloadCV(@PathVariable Long etudiantId) {
        try {
            // Retrieve the CV content from the service layer based on the article ID
            byte[] cvContent = etdservice.getCVByEtudiantId(etudiantId);

            // Set the response headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF); // Set content type as PDF
            headers.setContentDispositionFormData("attachment", "CV.pdf"); // Set filename for download

            // Return the CV content as a ResponseEntity
            return new ResponseEntity<>(cvContent, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @RequestMapping(method = RequestMethod.GET)
    public List<NouveauEtudiant> getAllEtudiants(){
        return etdservice.getAllEtudiants();
    }
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public NouveauEtudiant getNouveauEtudiantById(@PathVariable("id") Long id){
        return etdservice.getNouveauEtudiant(id);
    }
    @RequestMapping(method = RequestMethod.POST)
    public NouveauEtudiant createNouveauEtudiant(@RequestBody NouveauEtudiant etd){
        return etdservice.saveNouveauEtudiant(etd);
    }
    @RequestMapping(value = "/updateNouveauEtudiant",method = RequestMethod.PUT)
    public NouveauEtudiant updateNouveauEtudiant(@RequestBody NouveauEtudiant etd){
        return etdservice.updateNouveauEtudiant(etd);
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void deleteNouveauEtudiant(@PathVariable("id") Long id){
        etdservice.deleteNouveauEtudiantById(id);
    }

}
