package base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import base.model.Product;
import base.model.User;
import base.model.User.UserRole;
import base.model.UserOrder;
import base.repositories.OrderRepository;
import base.repositories.ProductRepository;
import base.repositories.UserRepository;


@SpringBootApplication
@ComponentScan(basePackages = { "base" }) 
public class Application extends SpringBootServletInitializer {
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootApplication.class);
	}

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
	@Bean
	public CommandLineRunner demo( UserRepository repository, ProductRepository productRepo, OrderRepository orderRepo) {
		return (args) -> {
				// add users
				User jens = new User("Jens", "Sels", "Jenssels1998@gmail.com", UserRole.User);
				User admin = new User("Admin", "Admin", "Admin@gmail.com", UserRole.Admin);
				repository.save(admin);
				repository.save(jens);
				// add products
				Product beer = new Product("Belgium Beer", "Best beer in the world", 1.50);
				Product vodka = new Product("Vodka", "Best drink in the world", 12.89);
				Product bread = new Product("Bulgarian Bread", "Bread sliced into big slices", 1.80);
				productRepo.save(vodka);
				productRepo.save(beer);
				productRepo.save(bread);
				// add orders
				orderRepo.save(new UserOrder(jens, vodka, 2));
				orderRepo.save(new UserOrder(jens, beer, 16));
				orderRepo.save(new UserOrder(jens, bread, 1));
				orderRepo.save(new UserOrder(admin, vodka, 8));

				
				// fetch all users
				log.info("Users found with findAll():");
				log.info("-------------------------------");
				for (User user : repository.findAll()) {
					log.info(user.toString());
				}
				log.info("");
				
				// fetch all products
				log.info("Products found with findAll():");
				log.info("-------------------------------");
				for (Product product : productRepo.findAll()) {
					log.info(product.toString());
				}
				log.info("");
				// fetch all orders
				log.info("Orders found with findAll():");
				log.info("-------------------------------");
				for (UserOrder order : orderRepo.findAll()) {
					log.info(order.toString());
				}
				log.info("");
				
				// fetch user by mail
				log.info("User found with findByMail():");
				log.info("-------------------------------");
				User user = repository.findByMail("Jenssels1998@gmail.com");
				log.info(user.toString());
				log.info("");
				
				// fetch user by findById
				repository.findById(jens.getId()).ifPresent(userById -> {
					log.info("User found with findById():");
					log.info("-------------------------------");
					log.info(userById.toString());
					log.info("");
				});
				
				// fetch all orders for user
				log.info("Orders found with findByUser():");
				log.info("-------------------------------");
				for (UserOrder order : orderRepo.findByUser(jens)) {
					log.info(order.toString());
				}
				log.info("");
		};
	}

}