package tn.iit.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tn.iit.dao.ClientRepository;
import tn.iit.dto.ClientDto;
import tn.iit.entity.Client;

@Service
@AllArgsConstructor
public class ClientService {
	private final ClientRepository clientRepository;

	public void save(ClientDto clientDto) {
		Client client = new Client();
		client.setCin(clientDto.getCin());
		client.setFirstName(clientDto.getFirstName());
		client.setLastName(clientDto.getLastName());
		clientRepository.save(client); // Le CIN est fourni directement
	}

	public List<ClientDto> findAll() {
		return clientRepository.findAll().stream()
				.map(client -> new ClientDto(client.getCin(), client.getFirstName(), client.getLastName()))
				.toList();
	}

	public void deleteByCin(String cin) {
		clientRepository.deleteById(cin);
	}

	public List<ClientDto> findByName(String key) {
		return clientRepository.findAll().stream()
				.filter(client -> client.getFirstName().equalsIgnoreCase(key) ||
						client.getLastName().equalsIgnoreCase(key))
				.map(client -> new ClientDto(client.getCin(), client.getFirstName(), client.getLastName()))
				.toList();
	}

	public Optional<ClientDto> findByCin(String cin) {
		return clientRepository.findById(cin).map(client ->
				new ClientDto(client.getCin(), client.getFirstName(), client.getLastName()));
	}

	public void update(ClientDto clientDto) {
		Optional<Client> existingClientOpt = clientRepository.findById(clientDto.getCin());
		if (existingClientOpt.isPresent()) {
			Client existingClient = existingClientOpt.get();
			existingClient.setFirstName(clientDto.getFirstName());
			existingClient.setLastName(clientDto.getLastName());
			clientRepository.save(existingClient);
		}
	}

	public List<String> findClientFirstNameByFirstName(String term) {
		return clientRepository.findAll().stream()
				.filter(client -> client.getFirstName().toLowerCase().contains(term.toLowerCase())) // Recherche par prénom uniquement
				.map(Client::getFirstName) // Retourne seulement le prénom
				.collect(Collectors.toList());
	}
}
