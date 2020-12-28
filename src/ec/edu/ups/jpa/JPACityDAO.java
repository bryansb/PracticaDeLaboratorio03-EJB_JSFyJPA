package ec.edu.ups.jpa;

import ec.edu.ups.dao.CityDAO;
import ec.edu.ups.entities.City;

public class JPACityDAO extends JPAGenericDAO<City, Integer>
	implements CityDAO {

    public JPACityDAO() {
	super(City.class);
    }

}
