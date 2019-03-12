package base.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import base.model.User;
import base.repositories.UserRepository;

@Controller
public class RegistrationController {
	
	@Autowired
	UserRepository userRepo;
	
	@GetMapping("/registerForm")
	public String registerFrom (@RequestParam(name="message", required=false, defaultValue="") String message, Model model, User user) {
		model.addAttribute("message", message);
		return "registerForm";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public RedirectView logout (HttpSession session, @Valid User user, BindingResult bindingResult, RedirectAttributes attributes) {
		
		if (bindingResult.hasErrors()) {
			attributes.addAttribute("message", "Please fill in valid values!");
			return new RedirectView("registerForm");
		} else if (userRepo.findByMail(user.getMail()) != null) {
			attributes.addAttribute("message", "Email already in use");
			return new RedirectView("registerForm");
		} else {
			userRepo.save(user);
			session.setAttribute("id", user.getId());
			session.setAttribute("name", user.getName());
			return new RedirectView("/home");
		}
	}

}
