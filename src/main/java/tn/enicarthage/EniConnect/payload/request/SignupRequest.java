package tn.enicarthage.EniConnect.payload.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignupRequest {

	 
	    @NotBlank
	    @Size(max = 50)
	    @Email
	    private String email;
	    
	    private Set<String> roles;
	    
	    @NotBlank
	    @Size(min = 6, max = 40)
	    private String password;
	  

	 

}
