package tn.iit.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/hello")
public class HelloController {
	@GetMapping("/home")
	public String goToHello(Model model) {
		model.addAttribute("date",LocalTime.now());
		return "hello"; //va a la page hello.html dans templates
	}
	@GetMapping("/home2")
	public ModelAndView goToHello2() {
		ModelAndView modelview =new ModelAndView();
		modelview.addObject("date",LocalDate.now());
		modelview.setViewName("hello");
		return modelview;
		}
}
