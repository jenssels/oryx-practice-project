package base.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import base.model.User;
import base.repositories.OrderRepository;
import base.repositories.UserRepository;

@Controller
public class LoginController {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	OrderRepository orderRepo;


	@GetMapping("/")
	public String welcome(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		
		return "index";
	}
	
	@GetMapping("/loginForm")
	public String loginForm (@RequestParam(name="name", required=false, defaultValue="World") String name, @RequestParam(name="message", required=false, defaultValue="") String message,  Model model) {
		model.addAttribute("name", name);
		model.addAttribute("message", message);

		return "loginForm";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public RedirectView  login (@RequestParam("mail") String mail, @RequestParam("password") String password , Model model, HttpSession session,  RedirectAttributes attributes) {
		User user = userRepo.findByMail(mail);
		if (user != null && user.getPassword().equals(password)) {
			session.setAttribute("id", user.getId());
			session.setAttribute("name", user.getName());
			return new RedirectView("home");
		} else {
			attributes.addAttribute("message", "Mail and password combination wrong!");
			return new RedirectView("loginForm");
		}
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public RedirectView logout (HttpSession session) {
		session.invalidate();
		return new RedirectView("/");
	}
	
	@GetMapping("/home")
	public String home (Model model, HttpSession session) {
		Long id = (Long) session.getAttribute("id");
		if (id != null) {
			userRepo.findById(id).ifPresent(user -> {
				model.addAttribute("user", user);
				model.addAttribute("orders", orderRepo.findByUser(user));
			});
			return "home";
		} else {
			model.addAttribute("message", "You have to be logged in to acces that page!");
			return "loginForm";
		}
		
	}
}
