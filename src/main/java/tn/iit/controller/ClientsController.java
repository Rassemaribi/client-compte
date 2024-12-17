package tn.iit.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;

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
	public String save(@RequestParam("id") int id, @RequestParam("firstName") String firstName,
					   @RequestParam("lastName") String lastName) {
		ClientDto clientDto = new ClientDto(id, firstName, lastName);
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
	public ResponseEntity<List<ClientDto>> searchClients(@RequestParam String key) {
		List<ClientDto> clients = service.findByName(key);
		return ResponseEntity.ok(clients);
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
