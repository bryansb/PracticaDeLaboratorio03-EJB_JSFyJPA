package ec.edu.ups.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import ec.edu.ups.ejb.BillHeadFacade;
import ec.edu.ups.ejb.ProductWarehouseFacade;
import ec.edu.ups.ejb.UserFacade;
import ec.edu.ups.entities.BillDetail;
import ec.edu.ups.entities.BillHead;
import ec.edu.ups.entities.User;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class BillHeadBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@EJB
	private BillHeadFacade ejbBillHeadFacade;
	
	@EJB
	private ProductWarehouseFacade productWarehouseFacade;
	
	@EJB
	private UserFacade ejbUserFacade;
	
	private User user;
	private BillHead billHead;
	
	private boolean userSelected;
	private List<BillDetail> billDetailList;
	
	public BillHeadBean() {
		super();
	}
	
	@PostConstruct
	public void init() {
		User u = new User();
		for (int i = 0; i < 10; i++) {
			u = new User();
			u.setName("n " + i);
			u.setLastname("L " + i);
			u.setEmail("e " + i + " " + new Date());
			u.setPassword("");
			u.setRole('c');
			u.setUsername("u " + i + " " + new Date());
			ejbUserFacade.create(u);
		}
		billDetailList = new ArrayList<>();
	}
	
	public String create() {
		this.billHead = new BillHead();
		this.billHead.setUser(this.user);
		return null;
	}
	
	public String confirm() {
		if ((this.billHead.getBillDetails() == null) 
				|| (this.billHead.getBillDetails().size() == 0)) {
			return null;
		}
		this.billHead.setDate(new GregorianCalendar());
		this.billHead.setStatus('A');
		this.billHead.calculateTotal();
		this.ejbBillHeadFacade.create(this.billHead);
		for (BillDetail billDetail: this.billHead.getBillDetails()) {
			productWarehouseFacade.update(billDetail.getProductWarehouse());
		}
		resetBilling();
		return null;
	}
	
	public String cancel(BillHead billHead) {
		billHead.setStatus('C');
		ejbBillHeadFacade.update(billHead);
		return null;
	}
	
	public String removeBillDetail(BillDetail billDetail) {
		int actualStock = billDetail.getProductWarehouse().getStock();
		int billDetailAmount = billDetail.getAmount(); 
		billDetail.getProductWarehouse().setStock(actualStock + billDetailAmount);
		this.billHead.getBillDetails().remove(billDetail);
		return null;
	}
	
	public String resetBilling() {
		billHead = null;
		user = null;
		userSelected = false;
		billDetailList = null;
		return null;
	}
	
	public String changeUser() {
		this.userSelected = false;
		return null;
	}
	
	public List<BillHead> getBillHeadList(){
		return this.ejbBillHeadFacade.findAll();
	}
	
	public List<User> getUserList() {
		return ejbUserFacade.findAll();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		this.userSelected = true;
		create();
	}

	public BillHead getBillHead() {
		return billHead;
	}

	public void setBillHead(BillHead billHead) {
		this.billHead = billHead;
	}

	public boolean isUserSelected() {
		return userSelected;
	}

	public void setUserSelected(boolean userSelected) {
		this.userSelected = userSelected;
	}

	public List<BillDetail> getBillDetailList() {
		return billDetailList;
	}

	public void setBillDetailList(List<BillDetail> billDetailList) {
		this.billDetailList = billDetailList;
	}
}
