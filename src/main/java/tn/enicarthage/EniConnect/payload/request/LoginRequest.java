package tn.enicarthage.EniConnect.payload.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginRequest {
	@NotBlank
	private String email;

	@NotBlank
	private String password;


}
