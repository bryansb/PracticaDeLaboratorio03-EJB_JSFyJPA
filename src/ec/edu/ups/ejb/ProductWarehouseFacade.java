package ec.edu.ups.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entities.ProductWarehouse;

@Stateless
public class ProductWarehouseFacade extends AbstractFacade<ProductWarehouse> {

	@PersistenceContext(unitName = "Practica03")
    private EntityManager em;
	
	public ProductWarehouseFacade() {
		super(ProductWarehouse.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public List<ProductWarehouse> findAllOrderedByProductName() {
		String[] order = {"product", "name"};
		return super.findByPath(null, null, order , 0, 0, true, false);
	}
	
	public List<ProductWarehouse> findByWarehouseId(int warehouseId) {
		String[][] attributes = {{"warehouse", "id"}, {"deleted"}};
		String[] values = {"equal&" + warehouseId, "equal&0"};
		String[] order = {"product", "name"};
		return super.findByPath(attributes, values, order, 0, 0, true, false);
	}
	
	public List<ProductWarehouse> findByCategoryId(int categoryId) {
		String[][] attributes = {{"product", "category", "id"}, {"deleted"}};
		String[] values = {"equal&" + categoryId, "equal&0"};
		String[] order = {"product", "name"};
		return super.findByPath(attributes, values, order, 0, 0, true, false);
	}
	
	public List<ProductWarehouse> findByWarehouseAndCategoryId(int warehouseId, int categoryId) {
		String[][] attributes = {{"product", "category", "id"}, {"warehouse", "id"}, {"deleted"}};
		String[] values = {"equal&" + categoryId, "equal&" + warehouseId, "equal&0"};
		String[] order = {"product", "name"};
		return super.findByPath(attributes, values, order, 0, 0, true, false);
	}

}
