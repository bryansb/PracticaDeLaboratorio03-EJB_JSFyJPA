package ec.edu.ups.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entities.BillHead;

@Stateless
public class BillHeadFacade extends AbstractFacade<BillHead>{

	@PersistenceContext(unitName = "Practica03")
    private EntityManager em;
	
    public BillHeadFacade() {
    	super(BillHead.class);
    }

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
