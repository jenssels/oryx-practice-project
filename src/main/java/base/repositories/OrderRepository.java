package base.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import base.model.User;
import base.model.UserOrder;

public interface OrderRepository extends CrudRepository<UserOrder, Long> {
	List<UserOrder> findByUser(User user);
	
	void deleteById(Long id);
}
