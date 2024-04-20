package tn.enicarthage.EniConnect.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.enicarthage.EniConnect.Repositories.AdminRepo;

@RequiredArgsConstructor
@Service
public class AdminService {
    private final AdminRepo adminRepository;


}
