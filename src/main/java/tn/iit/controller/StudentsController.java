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

import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import tn.iit.dto.StudentDto;
import tn.iit.service.StudentService;

@AllArgsConstructor
@Controller
@RequestMapping("/students")
public class StudentsController {
	private final StudentService service;

	@GetMapping({ "/", "/all", "/index" })
	public String findall(Model model) {
		model.addAttribute("students", service.findAll());
		return "students";
	}

	@PostMapping("/save")
	public String save(@RequestParam("id") int id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) {
		StudentDto studentDto = new StudentDto(id, firstName, lastName);
		service.save(studentDto);
		return "redirect:/students/";

	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable int id) {
		service.deleteById(id);
		return "redirect:/students/";
	}

	@PostMapping("/search")
	public ResponseEntity<List<StudentDto>> searchStudents(@RequestParam String key) {
		List<StudentDto> students = service.findByName(key);
		return ResponseEntity.ok(students);
	}

	@PostMapping("/edit")
	public String edit(@RequestParam("id") int id, Model model) {

		Optional<StudentDto> studentDtoOpt = service.findByid(id);
		if (studentDtoOpt.isPresent()) {
			model.addAttribute("student", studentDtoOpt.get());
		}
		return "students-edit";

	}
	@PostMapping("/update")
	public String update(@ModelAttribute StudentDto studentDto) {
		service.update(studentDto);
		return "redirect:/students/";
	}
}
