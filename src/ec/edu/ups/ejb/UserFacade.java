package ec.edu.ups.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entities.User;
import ec.edu.ups.utils.MathFunction;

@Stateless
public class UserFacade extends AbstractFacade<User> {

	@PersistenceContext(unitName = "Practica03")
    private EntityManager em;
	
    public UserFacade() {
    	super(User.class);
    }

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public User login(String email, String password) {
		User user;
		password = MathFunction.getMd5(password);
		String[][] attributes = {{"email"}, {"password"}};
		String[] values = {"equal&" + email, "equal&" + password};
		try {
			user = super.findByPath(attributes, values, null, 0, 1, false, false).get(0);
		} catch (Exception e) {
			user = null;
		}
		return user;
	}

}
