package tn.enicarthage.EniConnect.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.enicarthage.EniConnect.Repositories.AncienEtudiantRepo;

@RequiredArgsConstructor
@Service
public class AncienEtudiantService {
    private final AncienEtudiantRepo ancienEtudiantRepository;


}
