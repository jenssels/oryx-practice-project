package base.controller;

import javax.servlet.http.HttpSession;

import base.model.User;
import base.repositories.UserRepository;

public class AuthController {
	
	User user;
	public User getUserFromSession(HttpSession session, UserRepository userRepo) {
    	Long id = (Long) session.getAttribute("id");
        if (id != null && id != 0) {
			userRepo.findById(id).ifPresent(foundUser -> {
				this.user = foundUser;
			});
        }
        return this.user;
	}

}
