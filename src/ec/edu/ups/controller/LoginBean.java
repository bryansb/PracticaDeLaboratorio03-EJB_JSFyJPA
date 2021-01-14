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
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EJB
	private UserFacade ejbUserFacade;
		
	private String email;
	private String password;
	private List<User> userList;
	
	public LoginBean() {
		super();
	}
	
	@PostConstruct
	public void init() {
		userList = ejbUserFacade.findAll();
	}
	
	public String validate() {
		
		String password = MathFunction.getMd5(this.password);
		
		for (User user : userList) {
			if (user.getEmail().equals(email) && 
					user.getPassword().equals(password)) {
				
				return "Success";
			}
		}
		return "Error";
	}

	public UserFacade getEjbUserFacade() {
		return ejbUserFacade;
	}

	public void setEjbUserFacade(UserFacade ejbUserFacade) {
		this.ejbUserFacade = ejbUserFacade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
}
