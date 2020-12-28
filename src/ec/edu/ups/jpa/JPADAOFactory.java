package ec.edu.ups.jpa;

import ec.edu.ups.dao.BillDetailDAO;
import ec.edu.ups.dao.BillHeadDAO;
import ec.edu.ups.dao.CategoryDAO;
import ec.edu.ups.dao.CityDAO;
import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.ProductDAO;
import ec.edu.ups.dao.UserDAO;
import ec.edu.ups.dao.WarehouseDAO;

public class JPADAOFactory extends DAOFactory {

    @Override
    public BillDetailDAO getBillDetailDAO() {
	return new JPABillDetailDAO();
    }

    @Override
    public BillHeadDAO getBillHeadDAO() {
	return new JPABillHeadDAO();
    }

    @Override
    public CategoryDAO getCategoryDAO() {
	return new JPACategoryDAO();
    }

    @Override
    public CityDAO getCityDAO() {
	return new JPACityDAO();
    }

    @Override
    public ProductDAO getProductDAO() {
	return new JPAProductDAO();
    }

    @Override
    public UserDAO getUserDAO() {
	return new JPAUserDAO();
    }

    @Override
    public WarehouseDAO getWarehouseDAO() {
	return new JPAWarehouseDAO();
    }

}
