package tn.iit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
	private int id; // Peut être ignoré lors de l'ajout
	private String firstName;
	private String lastName;
}
