package tn.enicarthage.EniConnect.security.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.enicarthage.EniConnect.entities.Utilisateur;
import tn.enicarthage.EniConnect.repositories.UtilisateurRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UtilisateurRepository utilisateurRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    org.springframework.security.core.userdetails.User.UserBuilder builder = null;
    Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));

    return UserDetailsImpl.build(utilisateur);
  }

}
