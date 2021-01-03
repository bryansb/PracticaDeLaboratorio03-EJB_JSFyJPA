package ec.edu.ups.controller;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import ec.edu.ups.ejb.BillDetailFacade;
import ec.edu.ups.ejb.BillHeadFacade;
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
	private BillDetailFacade ejbBillDetailFacade;
	
	@EJB
	private UserFacade ejbUserFacade;
	
	private double subtotal;
	private double vat;
	private Calendar date;
	private char status;
	private double total;
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
	}
	
	public String create() {
		// Validar que no exista otro con estado de P
		String[][] attributes = {{"status"}, {"user", "email"}};
		String[] values = {"equal&P", "equal&" + user.getEmail()};
		
		List<BillHead> billHeads = ejbBillHeadFacade.findByPath( 
				attributes, values, null, 0, 1, true);
		if (billHeads == null || ( billHeads.size() == 0)) {
			BillHead billHead = new BillHead();
			billHead.setDate(Calendar.getInstance());
			billHead.setStatus('P');
			billHead.setUser(this.user);
			ejbBillHeadFacade.create(billHead);
			billHeads = ejbBillHeadFacade.findByPath( 
					attributes, values, null, 0, 1, true);
		}
		this.billHead = billHeads.get(0);
		return null;
	}
	
	public String confirm() {
		this.billHead.setDate(Calendar.getInstance());
		this.billHead.setStatus('A');
		this.ejbBillHeadFacade.update(this.billHead);
		this.billHead = null;
		this.user = null;
		this.userSelected = false;
		return null;
	}
	
	public String cancel(BillHead billHead) {
		billHead.setStatus('C');
		ejbBillHeadFacade.update(billHead);
		return null;
	}
	
	public String changeUser() {
		this.userSelected = false;
		return null;
	}
	
	public void loadBillHeadPending() {
		create();
		String[][] attributes = {{"billHead", "id"}};
		String[] values = {"equal&" + this.billHead.getId()};
		this.billDetailList = ejbBillDetailFacade.findByPath(attributes, 
				values, null, 0, 0, false);
		this.billHead.setBillDetails(billDetailList);
		this.billHead.calculateTotal();
	}
	
	public List<BillHead> getBillHeadList(){
		return this.ejbBillHeadFacade.findAll();
	}
	
	public List<User> getUserList() {
		return ejbUserFacade.findAll();
	}
	
	public double getSubtotal() {
		return subtotal;
	}
	
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	
	public double getVat() {
		return vat;
	}
	
	public void setVat(double vat) {
		this.vat = vat;
	}
	
	public Calendar getDate() {
		return date;
	}
	
	public void setDate(Calendar date) {
		this.date = date;
	}
	
	public char getStatus() {
		return status;
	}
	
	public void setStatus(char status) {
		this.status = status;
	}
	
	public double getTotal() {
		return total;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		this.userSelected = true;
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
		loadBillHeadPending();
		return billDetailList;
	}

	public void setBillDetailList(List<BillDetail> billDetailList) {
		this.billDetailList = billDetailList;
	}
}
