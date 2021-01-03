package ec.edu.ups.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import ec.edu.ups.ejb.BillHeadFacade;
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
	
	private double subtotal;
	private double vat;
	private Calendar date;
	private char status;
	private double total;
	private User user;
	private List<BillDetail> billDetails = new ArrayList<BillDetail>();
	
	public BillHeadBean() {
		super();
	}
	
	@PostConstruct
	public void init() {
	}
	
	public List<BillHead> getBillHeadList(){
		return this.ejbBillHeadFacade.findAll();
	}
	
	public String addBillDetail(BillDetail bd) {
		if (billDetails == null) {
			billDetails = new ArrayList<BillDetail>();
		}
		this.billDetails.add(bd);
		return null;
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
	}

	public List<BillDetail> getBillDetails() {
		return billDetails;
	}

	public void setBillDetails(List<BillDetail> billDetails) {
		this.billDetails = billDetails;
	}
	
	public String add() {
		BillHead billHead = new BillHead();
		billHead.setDate(this.date);
		billHead.setDeleted(false);
		billHead.setStatus('a');
		billHead.setSubtotal(this.subtotal);
		billHead.setTotal(this.total);
		billHead.setVat(this.vat);
		billHead.setUser(this.user);
		billHead.setBillDetails(billDetails);
		ejbBillHeadFacade.create(billHead);
		return null;
	}
	
	public String cancel(BillHead billHead) {
		billHead.setStatus('c');
		ejbBillHeadFacade.update(billHead);
		return null;
	}
}
