package ec.edu.ups.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entities.ProductDetail;

@Stateless
public class ProductDetailFacade extends AbstractFacade<ProductDetail> {

	@PersistenceContext(unitName = "Practica03")
    private EntityManager em;
	
	public ProductDetailFacade() {
		super(ProductDetail.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
