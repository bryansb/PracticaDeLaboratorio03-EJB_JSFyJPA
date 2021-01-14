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
import ec.edu.ups.utils.MathFunction;

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
	private List<User> userList;
	
	public UserBean() {
		super();
	}
	
	@PostConstruct
	public void init() {
		userList = ejbUserFacade.findAll();
	}
	
	public void userRandom() {
		for (int i = 0; i < 50; i++) {
			ejbUserFacade.create(new User( "correo"+i+"@mail.com", "001000"+i,
					MathFunction.getMd5("c"), "nombre"+i, "apellido"+i, 'C', false));
		}
		userList = ejbUserFacade.findAll();
	}
	
	public String create() {
		try {
			User user = new User();
			user.setEmail(email);
			user.setDni(dni);
			user.setPassword(MathFunction.getMd5("c"));
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
	
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

}
