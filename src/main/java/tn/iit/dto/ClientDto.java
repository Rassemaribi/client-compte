package tn.iit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
	private String cin; // Utilisation de "cin" comme identifiant unique
	private String firstName;
	private String lastName;
}