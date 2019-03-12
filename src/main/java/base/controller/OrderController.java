package base.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import base.model.User;
import base.model.UserOrder;
import base.repositories.OrderRepository;
import base.repositories.ProductRepository;
import base.repositories.UserRepository;

@Controller
public class OrderController {
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	ProductRepository productRepo;
	
	AuthController auth = new AuthController();
	
	UserOrder order = new UserOrder();

	@GetMapping("/orderForm")
	public String orderForm(@RequestParam("id") long orderId, @RequestParam(name="message", required=false, defaultValue="") String message, Model model, HttpSession session) {
		User user = auth.getUserFromSession(session, userRepo);
		if (user != null) {
			if (orderId != 0) {
				orderRepo.findById(orderId).ifPresent(foundOrder ->{
					order = foundOrder;
				});
			}
			System.out.println(order.getId());
			model.addAttribute("order", order);
			model.addAttribute("message", message);
			model.addAttribute("products", productRepo.findAll());
			
			return "orderForm";
		} else {
			return "loginForm";
		}
	}
	
	@RequestMapping(value = "/saveOrder", method = RequestMethod.POST)
	public RedirectView saveOrder (HttpSession session, @Valid @ModelAttribute("order") UserOrder order, BindingResult bindingResult, RedirectAttributes attributes) {
		User user = auth.getUserFromSession(session, userRepo);
		if (user != null) {
			if (bindingResult.hasErrors()) {
				attributes.addAttribute("id", order.getId());
				attributes.addAttribute("message", "Please fill in valid values!");
				return new RedirectView("orderForm");
			} else {
				order.setUser(user);
				orderRepo.save(order);
				return new RedirectView("home");
			}
		} else {
			return new RedirectView("loginForm");
		}
	}
	
	@GetMapping("/deleteOrder")
	public RedirectView deleteOrder (HttpSession session, @RequestParam("id") long orderId) {
		User user = auth.getUserFromSession(session, userRepo);
		if (user != null && orderId != 0) {
			orderRepo.findById(orderId).ifPresent(foundOrder ->{
					order = foundOrder;
			});
			if (user.getId() == order.getUser().getId()) {
				orderRepo.delete(order);
			}
			return new RedirectView("home");
		} else {
			return new RedirectView("loginForm");
		}
	}
}
