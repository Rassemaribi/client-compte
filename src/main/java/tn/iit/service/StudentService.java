package tn.iit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import tn.iit.dto.StudentDto;
@Service
public class StudentService {
private List<StudentDto> students = new ArrayList<>();

public StudentService() {
	students.add(new StudentDto(1,"Wiem" , "MILEDI"));
}
	
public void save(StudentDto studentdto) {
	students.add(studentdto);
}
public List<StudentDto> findAll(){
	return students;
}

public void deleteById(int id) {
	// TODO Auto-generated method stub
	students.removeIf(s->s.getId()== id);
}
// Méthode pour rechercher des étudiants par prénom ou nom
public List<StudentDto> findByName(String key) {
    return students.stream()
            .filter(student -> student.getFirstName().equalsIgnoreCase(key) || 
                               student.getLastName().equalsIgnoreCase(key))
            .collect(Collectors.toList());
}

public Optional<StudentDto> findByid(int id) {
	return students.stream().filter(s->s.getId()== id).findFirst();
	
}


public void update(StudentDto studentDto) {
	students.set(students.indexOf(studentDto), studentDto);
	
}
}
