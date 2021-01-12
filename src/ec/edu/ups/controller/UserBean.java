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
	
	private String email;
	private String dni;
	private String name;
	private String lastname;
	private boolean deleted;
	private List<User> userList;
	
	public UserBean() {
		super();
	}
	
	@PostConstruct
	public void init() {
		ejbUserFacade.create(new User("a", "a", "", "a", "a", 'C', false));
		ejbUserFacade.create(new User("b", "b", "", "b", "b", 'C', false));
		ejbUserFacade.create(new User("c", "c", "", "c", "c", 'C', false));
		userList = ejbUserFacade.findAll();
	}
	
	public String create() {
		try {
			User user = new User();
			user.setEmail(email);
			user.setDni(dni);
			user.setPassword("");
			user.setName(name);
			user.setLastname(lastname);
			user.setRole('C');
			ejbUserFacade.create(user);
			userList = ejbUserFacade.findAll();
			cleanString();
			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
	}
	
	public String update(User user) {
		user.setEditable(true);
		return "Success";

	}
	
	public String save(User user) {
		ejbUserFacade.update(user);
		user.setEditable(false);
		return "Success";
	}
	
	public String delete(User user) {
		try {
			user.setDeleted(true);
			ejbUserFacade.update(user);
			userList = ejbUserFacade.findAll();
			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
	}
	
	public String restore(User user) {
		try {
			user.setDeleted(false);
			ejbUserFacade.update(user);
			userList = ejbUserFacade.findAll();
			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
	}
	
	public void cleanString() {
		email = "";
		dni = "";
		name = "";
		lastname = "";
		deleted = false;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String username) {
		this.dni = username;
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

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

}
