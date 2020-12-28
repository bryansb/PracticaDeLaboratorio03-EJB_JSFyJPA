package ec.edu.ups.jpa;

import ec.edu.ups.dao.WarehouseDAO;
import ec.edu.ups.entities.Warehouse;

public class JPAWarehouseDAO extends JPAGenericDAO<Warehouse, Integer>
	implements WarehouseDAO {

    public JPAWarehouseDAO() {
	super(Warehouse.class);
    }

}
