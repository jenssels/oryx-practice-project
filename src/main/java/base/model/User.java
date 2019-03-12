package base.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
public class User {

	public enum UserRole{
	       User, Admin;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@NotNull
    @Size(min=2, max=30)
	private String name;
	@NotEmpty
	private String password;
    @NotNull
    @NotEmpty
    @Email
	private String mail;
    @Fetch(FetchMode.SELECT)
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<UserOrder> orders;
	private UserRole role;
	
	public User() {
		
	}
	
	public User(String name, String password, String mail) {
		this.name = name;
		this.password = password;
		this.mail = mail;
	}
	
	public User(String name, String password, String mail, UserRole role) {
		this.name = name;
		this.password = password;
		this.mail = mail;
		this.role = role;
	}
	
	public User(long id, String name, String password, String mail) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.mail = mail;
	}
	
	public User(long id, String name, String password, String mail, UserRole role) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.mail = mail;
		this.role = role;
	}
	
	public User(long id, String name, String password, String mail, List<UserOrder> orders) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.mail = mail;
		this.orders = orders;
	}
	
	public User(long id, String name, String password, String mail, List<UserOrder> orders, UserRole role) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.mail = mail;
		this.orders = orders;
		this.role = role;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
    public List<UserOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<UserOrder> orders) {
		this.orders = orders;
	}
	

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	@Override
    public String toString() {
        return String.format(
                "User[id=%d, name='%s', mail='%s, role='%s']",
                id, name, mail, role);
    }
	
}
