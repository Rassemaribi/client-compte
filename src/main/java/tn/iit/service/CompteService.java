package tn.iit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tn.iit.dao.ClientRepository;
import tn.iit.dao.CompteRepository;
import tn.iit.entity.Client;
import tn.iit.entity.Compte;
import tn.iit.exception.CompteNotFoundException;

@RequiredArgsConstructor
@Service
public class CompteService {

	@Autowired
	private final CompteRepository compteRepository;
	private final ClientRepository clientRepository;

	public void saveOrUpdate(Compte compte) {
		compteRepository.save(compte);
	}

	public List<Compte> findAll() {
		return compteRepository.findAll();
	}

	public void deleteById(Integer rib) {
		compteRepository.deleteById(rib);
	}

	public List<Compte> findAllByKey(String key) {
		return compteRepository.findByNomClientContains(key);
	}

	public Compte findById(Integer rib) {
		return compteRepository.findById(rib)
				.orElseThrow(() -> new CompteNotFoundException("Compte with rib = " + rib + " is not found"));
	}

	public List<String> findAccountNamesByTerm(String term) {
		return compteRepository.findAccountNamesByTerm(term);
	}

	public List<String> findCinByTerm(String term) {
		return clientRepository.findCinByTerm("%" + term + "%");
	}
	public String findClientNameByCin(String cin) {
		Client client = clientRepository.findByCin(cin);
		if (client != null) {
			return client.getNomClient(); // Retourne le nom du client
		}
		return null; // Retourne null si aucun compte n'est trouvé
	}

}