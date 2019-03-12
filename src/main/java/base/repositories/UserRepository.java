package base.repositories;

import org.springframework.data.repository.CrudRepository;

import base.model.User;

public interface UserRepository extends CrudRepository<User, Long>  {

	User findByMail(String mail);
	
	void deleteById(Long id);
}
