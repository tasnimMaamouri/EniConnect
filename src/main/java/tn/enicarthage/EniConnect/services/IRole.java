package tn.enicarthage.EniConnect.services;


import tn.enicarthage.EniConnect.entities.Role;

import java.util.List;

public interface IRole {
    public Role addRole(Role roleDto);
    public List<Role> getAllRole();
    public Role getRoleById(Long id);
    public Role getRoleByNom(String nom);
    public Role updateRole(Role roleDto,Long id);
    public void deleteRole(Long id);
}
