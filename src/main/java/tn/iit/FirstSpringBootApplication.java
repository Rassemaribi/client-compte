package tn.iit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FirstSpringBootApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FirstSpringBootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello Spring");
	}
	
	

}
/*

//package tn.iit;



import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;



import jakarta.transaction.Transactional;

import tn.iit.dao.CompteRepository;

import tn.iit.entity.Compte;



@SpringBootApplication

public class FirstSpringBootApplication implements CommandLineRunner {



	@Autowired

	private CompteRepository compteRepository;



	public static void main(String[] args) {

		SpringApplication.run(FirstSpringBootApplication.class, args);

	}



	@Transactional // --> commit

	@Override

	public void run(String... args) throws Exception {



		compteRepository.flush();



		List<Compte> comptes = compteRepository.findByNomClientAndSolde1("Makhlouf", 150);

		System.out.println(comptes);

		comptes = compteRepository.findByNomClientAndSolde2("Makhlouf", 150);

		System.out.println(comptes);

		comptes = compteRepository.findByNomClientAndSolde3("Makhlouf", 150);

		System.out.println(comptes);

		comptes = compteRepository.findByNomClientAndSoldeGreaterThanEqual("Makhlouf", 150);

		System.out.println(comptes);

		// compteRepository.deleteById(3);



		/*

		 * Optional<Compte> c4Opt = compteRepository.findById(1); if (c4Opt.isPresent())

		 * { Compte c4 = c4Opt.get(); c4.setSolde(c4.getSolde() + 500);

		 * compteRepository.save(c4); System.out.println(c4); }

		 */



		/*

		 * Compte c1 = new Compte("Racem", 10000); compteRepository.save(c1);

		 * 

		 * Compte c2 = new Compte("Makhlouf", 200); compteRepository.save(c2);

		 * 

		 * Compte c3 = new Compte("Ahmed", 10); compteRepository.save(c3);

		 

	}



}*/
