package tn.iit.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;
import tn.iit.dto.ClientDto;
import tn.iit.service.ClientService;

@AllArgsConstructor
@Controller
@RequestMapping("/clients")
public class StudentsController {
	private final ClientService service;

	@GetMapping({ "/", "/all", "/index" })
	public String findall(Model model) {
		model.addAttribute("students", service.findAll());
		return "clients";
	}

	@PostMapping("/save")
	public String save(@RequestParam("id") int id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) {
		ClientDto studentDto = new ClientDto(id, firstName, lastName);
		service.save(studentDto);
		return "redirect:/students/";

	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable int id) {
		service.deleteById(id);
		return "redirect:/students/";
	}

	@PostMapping("/search")
	public ResponseEntity<List<ClientDto>> searchStudents(@RequestParam String key) {
		List<ClientDto> students = service.findByName(key);
		return ResponseEntity.ok(students);
	}

	@PostMapping("/edit")
	public String edit(@RequestParam("id") int id, Model model) {

		Optional<ClientDto> studentDtoOpt = service.findByid(id);
		if (studentDtoOpt.isPresent()) {
			model.addAttribute("student", studentDtoOpt.get());
		}
		return "clients-edit";

	}
	@PostMapping("/update")
	public String update(@ModelAttribute ClientDto studentDto) {
		service.update(studentDto);
		return "redirect:/students/";
	}
}
