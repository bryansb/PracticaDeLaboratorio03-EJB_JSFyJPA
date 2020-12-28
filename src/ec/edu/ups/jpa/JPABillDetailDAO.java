package ec.edu.ups.jpa;

import ec.edu.ups.dao.BillDetailDAO;
import ec.edu.ups.entities.BillDetail;

public class JPABillDetailDAO extends JPAGenericDAO<BillDetail, Integer>
	implements BillDetailDAO {

    public JPABillDetailDAO() {
	super(BillDetail.class);
    }

}
