package tn.enicarthage.EniConnect.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.EniConnect.entities.Role;
import tn.enicarthage.EniConnect.services.IRole;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/GRole")

public class ControllerRole {
    @Autowired
    private IRole roleService;

    @PostMapping("/AddRole")
    public ResponseEntity<Role> addRole(@RequestBody Role role) {
        try {
            Role roleCre=roleService.getRoleByNom(role.getNom());
            if(roleCre!=null){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            else {
                return new ResponseEntity<>(roleService.addRole(role), HttpStatus.CREATED);
            }
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/AllRole")
    public ResponseEntity<List<Role>> getAllRole(){
        try {
            if(roleService.getAllRole().isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(roleService.getAllRole(), HttpStatus.OK) ;
            }
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/Role/{idRole}")
    public ResponseEntity<Role> getRoleById(@PathVariable ("idRole") Long idRole ) {
        try {

            Role resultat=roleService.getRoleById(idRole);
            if (resultat!=null) {
                return new ResponseEntity<>(resultat, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/UpdateRole/{idRole}")
    public ResponseEntity<Role> updateRole(@RequestBody Role role,@PathVariable ("idRole")Long idRole) {
        try {
            if (roleService.getRoleById(idRole) != null) {
                Role roleByNom = roleService.getRoleByNom(role.getNom());


                if ( roleByNom != null) {

                    return new ResponseEntity<>(HttpStatus.CONFLICT);
                } else {
                    return new ResponseEntity<>(roleService.updateRole(role, idRole), HttpStatus.OK);


                }

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/DeleteRole/{idRole}")
    public ResponseEntity<HttpStatus> deleteRole(@PathVariable("idRole")Long idRole) {

        if (roleService.getRoleById(idRole)!=null) {
            roleService.deleteRole(idRole);
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }
}
