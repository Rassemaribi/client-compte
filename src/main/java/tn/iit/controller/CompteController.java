package tn.iit.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import tn.iit.entity.Compte;
import tn.iit.service.CompteService;

@AllArgsConstructor
@Controller
@RequestMapping("/compte")
public class CompteController {

	private final CompteService service;

	// Afficher tous les comptes
	@GetMapping({ "/", "/all", "/index" })
	public String findAll(Model model) {
		model.addAttribute("comptes", service.findAll());
		return "comptes"; // Assurez-vous que cette vue existe
	}

	// Enregistrer un nouveau compte
	@PostMapping("/save")
	public String save(@RequestParam String nomClient, @RequestParam float solde) {
		// Si 'dorra' est un champ obligatoire, assurez-vous de l'ajouter ici
		Compte compteEntity = new Compte(nomClient, solde); // Ajoutez 'dorra' si nécessaire
		service.saveOrUpdate(compteEntity);
		return "redirect:/compte/"; // Redirection après l'enregistrement
	}

	// Supprimer un compte
	@DeleteMapping("/delete/{rib}")
	public ResponseEntity<Void> delete(@PathVariable Integer rib) {
		try {
			service.deleteById(rib); // Suppression par RIB
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/search")
	public String searchCompte(@RequestParam String key, Model model) {
	    List<Compte> comptes = service.findAllByKey(key);
	    model.addAttribute("comptes", comptes);
	    return "comptes"; // Recharge la vue avec les résultats
	}



	// Charger les informations d'un compte pour modification
	@GetMapping("/edit/{rib}")
	public String edit(@PathVariable Integer rib, Model model) {
		Compte compte = service.findById(rib);
		model.addAttribute("compte", compte);
		return "compte-edit"; // Vue pour modifier le compte
	}

	// Mettre à jour un compte
	@PostMapping("/update")
	public String update(@ModelAttribute Compte compteEntity) {
		service.saveOrUpdate(compteEntity);
		return "redirect:/compte/"; // Redirection après mise à jour
	}
}
