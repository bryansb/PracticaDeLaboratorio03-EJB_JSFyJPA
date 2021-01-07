package ec.edu.ups.ejb;

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

}
