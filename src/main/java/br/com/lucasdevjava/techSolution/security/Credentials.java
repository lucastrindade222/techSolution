package  br.com.lucasdevjava.techSolution.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credentials  {
	private static final long serialVersionUID = 1L;

	private String email ;
	private String password;
}
