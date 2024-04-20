package tn.enicarthage.EniConnect.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Period;

@RestController
@RequiredArgsConstructor
public class AdminController {

    @GetMapping("/calculate-age")
    public ResponseEntity<Integer> calculateAge(@RequestParam("dateOfBirth") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth) {
        int age = calculateAgeFromDateOfBirth(dateOfBirth);

        return ResponseEntity.ok(age);
    }

    private int calculateAgeFromDateOfBirth(LocalDate dateOfBirth) {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(dateOfBirth, currentDate);
        return period.getYears();
    }
}


