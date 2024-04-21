package tn.enicarthage.EniConnect.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.EniConnect.entities.Role;
import tn.enicarthage.EniConnect.entities.Utilisateur;
import tn.enicarthage.EniConnect.payload.request.LoginRequest;
import tn.enicarthage.EniConnect.payload.request.SignupRequest;
import tn.enicarthage.EniConnect.payload.response.JwtResponse;
import tn.enicarthage.EniConnect.payload.response.MessageResponse;
import tn.enicarthage.EniConnect.repositories.RoleRepository;
import tn.enicarthage.EniConnect.repositories.UtilisateurRepository;
import tn.enicarthage.EniConnect.security.jwt.JwtUtils;
import tn.enicarthage.EniConnect.security.services.UserDetailsImpl;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class ControllerAuth {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UtilisateurRepository utilisateurRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt,
				userDetails.getId(),
				userDetails.getEmail(),
				roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {


		if (utilisateurRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		Utilisateur utilisateur = new Utilisateur(
				signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));
		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();
		if (strRoles == null) {
			Role ancienetudiantRole = roleRepository.findByNom("ROLE_NOUVEAUETUDIANT")
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(ancienetudiantRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
					case "ROLE_ADMIN":
						Role adminRole = roleRepository.findByNom("ROLE_ADMIN")
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(adminRole);
						break;
					case "ROLE_ANCIENETUDIANT":
						Role ancienetudiantRole = roleRepository.findByNom("ROLE_ANCIENETUDIANT")
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(ancienetudiantRole);
						break;
					default:
						Role NouveauEtudiantRole = roleRepository.findByNom("ROLE_NOUVEAUETUDIANT")
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(NouveauEtudiantRole);
				}
			});
		}
		utilisateur.setRoles(roles);
		utilisateurRepository.save(utilisateur);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
