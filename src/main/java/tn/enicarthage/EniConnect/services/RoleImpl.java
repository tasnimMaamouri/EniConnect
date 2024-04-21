package tn.enicarthage.EniConnect.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.enicarthage.EniConnect.entities.Role;
import tn.enicarthage.EniConnect.repositories.RoleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleImpl implements IRole {
    @Autowired
    RoleRepository roleR;
    @Override
    public Role addRole(Role role) {
        Role roleSaved = roleR.save(role);
        return roleSaved;
    }

    @Override
    public List<Role> getAllRole() {
        List<Role> roles=new ArrayList<Role>();
        for(Role role:roleR.findAll()) {
            roles.add(role);
        }
        return roles;
    }

    @Override
    public Role getRoleById(Long id) {
        if(roleR.findById(id).isPresent()){
            return roleR.findById(id).get();
        }else {
            return null;
        }
    }

    @Override
    public Role getRoleByNom(String nom) {
        Optional<Role> resultat=roleR.findByNom(nom);
        if(resultat.isPresent()){
            return resultat.get();
        }else {
            return null;
        }
    }

    @Override
    public Role updateRole(Role role, Long id) {
        role.setId(id);
        return roleR.save(role);
    }

    @Override
    public void deleteRole(Long id) {
roleR.deleteById(id);
    }
}
