package tn.iit.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import tn.iit.dto.ClientDto;
import tn.iit.service.ClientService;

@AllArgsConstructor
@Controller
@RequestMapping("/clients")
public class ClientsController {
	private final ClientService service;

	@GetMapping({ "/", "/all", "/index" })
	public String findall(Model model) {
		model.addAttribute("clients", service.findAll());
		return "clients";
	}

	@PostMapping("/save")
	public String save(@RequestParam("firstName") String firstName,
					   @RequestParam("lastName") String lastName) {
		ClientDto clientDto = new ClientDto(0, firstName, lastName); // L'ID est ignoré ici
		service.save(clientDto);
		return "redirect:/clients/";
	}


	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		try {
			service.deleteById(id); // Suppression par ID
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}


	@PostMapping("/search")
	public String searchClients(@RequestParam String key, Model model) {
		List<ClientDto> clients = service.findByName(key);
		model.addAttribute("clients", clients); // Ajouter la liste des clients au modèle
		return "clients"; // Retourner le nom de la vue Thymeleaf
	}
	@GetMapping("/autocomplete")
	@ResponseBody
	public List<String> autocomplete(@RequestParam String term) {
		return service.findClientFirstNameByFirstName(term); // Recherche par prénom uniquement
	}

	@PostMapping("/edit")
	public String edit(@RequestParam("id") int id, Model model) {

		Optional<ClientDto> clientDtoOpt = service.findByid(id);
		if (clientDtoOpt.isPresent()) {
			model.addAttribute("client", clientDtoOpt.get());
		}
		return "clients-edit";

	}
	@PostMapping("/update")
	public String update(@ModelAttribute ClientDto clientDto) {
		service.update(clientDto);
		return "redirect:/clients/";
	}
}