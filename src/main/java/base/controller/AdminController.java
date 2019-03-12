package base.controller;

import java.util.List;

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

import base.model.Product;
import base.model.User;
import base.model.User.UserRole;
import base.model.UserOrder;
import base.model.UserOrder.OrderStatus;
import base.repositories.OrderRepository;
import base.repositories.ProductRepository;
import base.repositories.UserRepository;

@Controller
public class AdminController {
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	ProductRepository productRepo;
	
	AuthController auth = new AuthController();
	
	Product product = new Product();
	UserOrder order = new UserOrder();
	
	@GetMapping("/adminHome")
	public String adminHome (HttpSession session, Model model) {
		User user = auth.getUserFromSession(session, userRepo);
		if (user != null) {
			if (user.getRole() == UserRole.Admin) {
				List<User> users = (List<User>) userRepo.findAll();
				List<UserOrder> orders = (List<UserOrder>) orderRepo.findAll();
				List<Product> products = (List<Product>) productRepo.findAll();
				model.addAttribute("user",user);
				model.addAttribute("users", users);
				model.addAttribute("orders", orders);
				model.addAttribute("products", products);
				return "adminHome";
			} else {
				return "home";
			}
		} else {
			return "loginForm";
		}
	}
	
	@GetMapping("/deleteAdminUser")
	public RedirectView deleteUser (@RequestParam("id") Long userId,HttpSession session, Model model) {
		User user = auth.getUserFromSession(session, userRepo);
		if (user != null) {
			if (user.getRole() == UserRole.Admin) {
				userRepo.deleteById(userId);
				return new RedirectView("adminHome");
			} else {
				return new RedirectView("home");
			}
		} else {
			return new RedirectView("loginForm");
		}
	}
	
	@GetMapping("/deleteAdminOrder")
	public RedirectView deleteOrder (@RequestParam("id") Long orderId,HttpSession session, Model model) {
		User user = auth.getUserFromSession(session, userRepo);
		if (user != null) {
			if (user.getRole() == UserRole.Admin) {
				orderRepo.deleteById(orderId);
				return new RedirectView("adminHome");
			} else {
				return new RedirectView("home");
			}
		} else {
			return new RedirectView("loginForm");
		}
	}
	
	@GetMapping("/sendAdminOrder")
	public RedirectView sendOrder (@RequestParam("id") Long orderId,HttpSession session, Model model) {
		User user = auth.getUserFromSession(session, userRepo);
		order = new UserOrder();
		if (user != null) {
			if (user.getRole() == UserRole.Admin && orderId != null) {
				orderRepo.findById(orderId).ifPresent(foundOrder ->{
					order = foundOrder;
				});
				if (order.getStatus() == OrderStatus.Paid) {
					order.setStatus(OrderStatus.Transit);
					orderRepo.save(order);
				}
				return new RedirectView("adminHome");
			} else {
				return new RedirectView("home");
			}
		} else {
			return new RedirectView("loginForm");
		}
	}
	
	@GetMapping("/adminProductForm")
	public String productForm(@RequestParam("id") long productId, @RequestParam(name="message", required=false, defaultValue="") String message, Model model, HttpSession session) {
		User user = auth.getUserFromSession(session, userRepo);
		product = new Product();
		if (user != null) {
			if (user.getRole() == UserRole.Admin) {
				productRepo.findById(productId).ifPresent(foundProduct -> {
					this.product = foundProduct;
				});
				model.addAttribute("product", product);
				model.addAttribute("message", message);
				return "adminProductForm";
			} else {
				return "home";
			}
		} else {
			return "loginForm";
		}
	}
	
	@RequestMapping(value = "/adminSaveProduct", method = RequestMethod.POST)
	public RedirectView saveProduct (HttpSession session, @Valid @ModelAttribute("product") Product product, BindingResult bindingResult, RedirectAttributes attributes) {
		User user = auth.getUserFromSession(session, userRepo);
		if (user != null) {
			if (user.getRole() == UserRole.Admin) {
				if (bindingResult.hasErrors()) {
					attributes.addAttribute("id", product.getId());
					attributes.addAttribute("message", "Please fill in valid values!");
					return new RedirectView("adminProductForm");
				} else {
					productRepo.save(product);
					return new RedirectView("adminHome");
				}
			} else {
				return new RedirectView("home");
			}
		} else {
			return new RedirectView("loginForm");
		}
	}
}
