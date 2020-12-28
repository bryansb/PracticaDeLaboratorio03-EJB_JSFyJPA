package ec.edu.ups.jpa;

import ec.edu.ups.dao.BillHeadDAO;
import ec.edu.ups.entities.BillHead;

public class JPABillHeadDAO extends JPAGenericDAO<BillHead, Integer>
	implements BillHeadDAO {

    public JPABillHeadDAO() {
	super(BillHead.class);
    }

}
