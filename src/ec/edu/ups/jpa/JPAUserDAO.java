package ec.edu.ups.jpa;

import ec.edu.ups.dao.UserDAO;
import ec.edu.ups.entities.User;

public class JPAUserDAO extends JPAGenericDAO<User, Integer>
	implements UserDAO {

    public JPAUserDAO() {
	super(User.class);
    }

}
