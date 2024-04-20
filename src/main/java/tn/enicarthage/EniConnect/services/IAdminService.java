package tn.enicarthage.EniConnect.services;

import tn.enicarthage.EniConnect.entities.Admin;

import java.util.List;

public interface IAdminService {

    List<Admin> getAdmins();

    Admin getAdminById(Long adminId);

    void createAdmin(Admin admin);

    void deleteAdminById(Long adminId);

    void updateAdminById(Long adminId, Admin newAdmin);
}
