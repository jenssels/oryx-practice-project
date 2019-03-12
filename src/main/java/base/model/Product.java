package base.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@NotEmpty
	private String name;
	@NotEmpty
	private String description;
	@DecimalMin("0.01") 
	private Double price;
	@Fetch(FetchMode.SELECT)
	@OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
	private List<UserOrder> orders;
	
	public Product() {
		
	}
	
	public Product(String name, String description, Double price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	public Product(long id, String name, String description, Double price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	public Product(long id, String name, String description, Double price, List<UserOrder> orders) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.orders = orders;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<UserOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<UserOrder> orders) {
		this.orders = orders;
	}
	
	public int getTotalOrdered() {
		int amount = 0;
		for(UserOrder order : orders) {
			amount += order.getAmount();
		}
		return amount;
	}

	@Override
	public String toString() {
		return "Product [id =" + id + ", name=" + name + ", description=" + description + ", price=" + price + "]";
	}
	
	
}
