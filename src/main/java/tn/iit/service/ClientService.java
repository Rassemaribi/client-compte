package tn.iit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import tn.iit.dto.ClientDto;
@Service
public class ClientService {
private List<ClientDto> students = new ArrayList<>();

public ClientService() {
	students.add(new ClientDto(1,"Wiem" , "MILEDI"));
}
	
public void save(ClientDto studentdto) {
	students.add(studentdto);
}
public List<ClientDto> findAll(){
	return students;
}

public void deleteById(int id) {
	// TODO Auto-generated method stub
	students.removeIf(s->s.getId()== id);
}
// Méthode pour rechercher des étudiants par prénom ou nom
public List<ClientDto> findByName(String key) {
    return students.stream()
            .filter(student -> student.getFirstName().equalsIgnoreCase(key) || 
                               student.getLastName().equalsIgnoreCase(key))
            .collect(Collectors.toList());
}

public Optional<ClientDto> findByid(int id) {
	return students.stream().filter(s->s.getId()== id).findFirst();
	
}


public void update(ClientDto studentDto) {
	students.set(students.indexOf(studentDto), studentDto);
	
}
}
