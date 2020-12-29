package ec.edu.ups.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entities.User;

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

}
