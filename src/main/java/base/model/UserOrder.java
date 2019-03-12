package base.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class UserOrder {
	
	public enum OrderStatus {
		Pending, Paid, Transit, Received
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	@ManyToOne
	@JoinColumn(name = "product_id")
	@NotNull
	private Product product;
	@Min(1)
	private int amount;
	
	private OrderStatus status = OrderStatus.Pending;
	
	public UserOrder () {
		
	}
	
	public UserOrder(User user, Product product, int amount) {
		this.user = user;
		this.product = product;
		this.amount = amount;
	}
	
	public UserOrder(long id, User user, Product product, int amount) {
		this.id = id;
		this.user = user;
		this.product = product;
		this.amount = amount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Order [id =" + id + ", user=" + user.getName() + ", product=" + product.getName() + ", amount=" + amount + ", status=" + status + "]";
	}
	
	public Double getTotalPrice() {
		return this.product.getPrice() * amount;
	}
	
}
