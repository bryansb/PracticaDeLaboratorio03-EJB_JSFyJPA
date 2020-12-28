package ec.edu.ups.dao;

import ec.edu.ups.jpa.JPADAOFactory;

public abstract class DAOFactory {
	
	protected static DAOFactory factory = new JPADAOFactory();
	
	public static DAOFactory getFactory() {
		return factory;
	}
	
	public abstract BillDetailDAO getBillDetailDAO();
	
	public abstract BillHeadDAO getBillHeadDAO();
	
	public abstract CategoryDAO getCategoryDAO();
	
	public abstract CityDAO getCityDAO();
	
	public abstract ProductDAO getProductDAO();
	
	public abstract UserDAO getUserDAO();
	
	public abstract WarehouseDAO getWarehouseDAO();

}