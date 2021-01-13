package ec.edu.ups.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import ec.edu.ups.ejb.BillDetailFacade;
import ec.edu.ups.ejb.BillHeadFacade;
import ec.edu.ups.ejb.ProductWarehouseFacade;
import ec.edu.ups.entities.BillDetail;
import ec.edu.ups.entities.BillHead;
import ec.edu.ups.entities.ProductWarehouse;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class BillDetailBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EJB
	private BillHeadFacade ejbBillHeadFacade;
	
	@EJB
	private BillDetailFacade ejbBillDetailFacade;
	
	@EJB
	private ProductWarehouseFacade ejbProductWarehouseFacade;
	
	private int amount;
	private ProductWarehouse productWarehouse;
	private BillHead billHead;
	private List<ProductWarehouse> productWarehouseList;
	private String current;
	
	private boolean inputAmount;
	
	public BillDetailBean() {
		this.amount = 1;
	}
	
	@PostConstruct
	public void init() {
		for (int i = 0; i < 10; i++) {
			ProductWarehouse pd = new ProductWarehouse();
			pd.setPrice((i  + 1) * 100.0);
			pd.setStock((i  + 1) * 10);
			ejbProductWarehouseFacade.create(pd);
		}
		this.productWarehouseList = ejbProductWarehouseFacade.findAll();
	}
	
	public List<BillDetail> getBillDetailList() {
		return ejbBillDetailFacade.findAll();
	}
	
	public List<ProductWarehouse> getProductWarehouseList() {
		return this.productWarehouseList;
	}
	
	public String create(BillHead billHead) {
		if(this.amount <= this.productWarehouse.getStock()) {
			billHead.createBillDetail(this.amount, 
					this.productWarehouse.getPrice(), 
					this.productWarehouse, billHead);
			this.inputAmount = false;
			this.productWarehouse.setStock(this.productWarehouse.getStock() 
					- this.amount);
		}
		this.amount = 1;
		billHead.calculateTotal();
		return null;
	}
	
	public String validateAmount() {
		if(this.amount <= this.productWarehouse.getStock()) {
			
		}
		return null;
	}
	
	public String cancelBilling() {
		productWarehouseList = ejbProductWarehouseFacade.findAll();
		inputAmount = false;
		return null;
	}
	
	public String delete(BillDetail billDetail) {
		ejbBillDetailFacade.delete(billDetail);
		return null;
	}
	
	public String loadProduct(ProductWarehouse productWarehouse) {
		this.amount = 1;
		this.inputAmount = true;
		this.productWarehouse = productWarehouse;
		return null;
	}

	public String cancelProduct() {
		this.amount = 1;
		this.inputAmount = false;
		this.productWarehouse = null;
		return null;
	}
	
	public String cancelBillDetail(BillHead billHead) {
		for (BillDetail billDetail : billHead.getBillDetails()) {
			ProductWarehouse productWarehouse = billDetail.getProductWarehouse();
			int amount = billDetail.getAmount();
			int stock = billDetail.getProductWarehouse().getStock();
			productWarehouse.setStock(stock + amount);
			ejbProductWarehouseFacade.update(productWarehouse);
		}
		productWarehouseList = ejbProductWarehouseFacade.findAll();
		return null;
	}
	
	public BillHeadFacade getEjbBillHeadFacade() {
		return ejbBillHeadFacade;
	}

	public void setEjbBillHeadFacade(BillHeadFacade ejbBillHeadFacade) {
		this.ejbBillHeadFacade = ejbBillHeadFacade;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public ProductWarehouse getProductWarehouse() {
		return productWarehouse;
	}

	public void setProductWarehouse(ProductWarehouse productDetail) {
		this.productWarehouse = productDetail;
	}

	public BillHead getBillHead() {
		return billHead;
	}

	public void setBillHead(BillHead billHead) {
		this.billHead = billHead;
	}

	public boolean isInputAmount() {
		return inputAmount;
	}

	public void setInputAmount(boolean inputAmount) {
		this.inputAmount = inputAmount;
	}

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}
	
	public String printCurrent() {
		ProductWarehouse p = ejbProductWarehouseFacade.read(Integer.parseInt(current));
		System.out.println(p.getStock());
		return null;
	}
	
	
}
