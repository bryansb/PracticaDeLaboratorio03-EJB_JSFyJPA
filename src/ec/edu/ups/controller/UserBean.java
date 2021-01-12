package ec.edu.ups.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import ec.edu.ups.ejb.UserFacade;
import ec.edu.ups.entities.User;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class UserBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private UserFacade ejbUserFacade;
	
	private int id;
	private String email;
	private String username;
	private String password;
	private String name;
	private String lastname;
	private char role;
	private boolean deleted;
	
	public UserBean() {
		super();
	}
	
	@PostConstruct
	public void init() {
	}
	
	public String create() {
		try {
			User user = new User();
			user.setEmail(email);
			user.setUsername(username);
			user.setPassword(password);
			user.setName(name);
			user.setName(lastname);
			user.setRole(role);
			user.setDeleted(false);
			ejbUserFacade.create(user);
			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
	}
	
	public List<User> getUserList() {
		return ejbUserFacade.findAll();
	}
	
	public String update(User user) {
		try {
			user.setEmail(email);
			user.setUsername(username);
			user.setPassword(password);
			user.setName(name);
			user.setName(lastname);
			user.setRole(role);
			user.setDeleted(deleted);
			ejbUserFacade.update(user);
			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
	}
	
	public String delete(User user) {
		try {
			user.setDeleted(true);
			ejbUserFacade.update(user);
			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
	}
	
	public UserFacade getEjbUserFacade() {
		return ejbUserFacade;
	}

	public void setEjbUserFacade(UserFacade ejbUserFacade) {
		this.ejbUserFacade = ejbUserFacade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public char getRole() {
		return role;
	}

	public void setRole(char role) {
		this.role = role;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
