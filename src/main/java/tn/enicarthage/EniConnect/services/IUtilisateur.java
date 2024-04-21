package tn.enicarthage.EniConnect.services;


import tn.enicarthage.EniConnect.entities.Utilisateur;

public interface IUtilisateur {
    public Utilisateur getUtilisateurByEmail(String email);
    public Utilisateur updateUtilisateur(Utilisateur utilisateur, Long id);

        }
