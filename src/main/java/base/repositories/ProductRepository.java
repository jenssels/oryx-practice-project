package base.repositories;

import org.springframework.data.repository.CrudRepository;

import base.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{
	
}
