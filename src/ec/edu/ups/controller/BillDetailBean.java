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
	
	//TEMP
	@EJB
	private ProductWarehouseFacade ejbProductDetailFacade;
	
	private int amount;
	private double unitPrice;
	private double total;
	private ProductWarehouse productDetail;
	private BillHead billHead;
	private String productName;
	
	private boolean inputAmount;
	
	public BillDetailBean() {
		
	}
	
	@PostConstruct
	public void init() {
		for (int i = 0; i < 10; i++) {
			ProductWarehouse pd = new ProductWarehouse();
			pd.setPrice((i  + 1) * 100.0);
			pd.setStock((i  + 1) * 10);
			ejbProductDetailFacade.create(pd);
		}
	}
	
	public List<BillDetail> getBillDetailList() {
		return ejbBillDetailFacade.findAll();
	}
	
	public List<ProductWarehouse> getProductDetailList() {
		return ejbProductDetailFacade.findAll();
	}
	
	public String create(BillHead billHead) {
		if(this.amount <= this.productDetail.getStock()) {
			BillDetail b = new BillDetail();
			b.setAmount(this.amount);
			b.setUnitPrice(this.unitPrice);
			b.setTotal(this.total);
			b.setDeleted(false);
			b.setProductDetail(this.productDetail);
			b.setBillHead(billHead);
			ejbBillDetailFacade.create(b);
			this.productDetail.setStock(this.productDetail.getStock() - this.amount);
			this.ejbProductDetailFacade.update(this.productDetail);
			this.inputAmount = false;
		}
		this.amount = 1;
		return null;
	}
	
	public String delete(BillDetail billDetail) {
		ejbBillDetailFacade.delete(billDetail);
		return null;
	}
	
	public String loadProduct(ProductWarehouse pd) {
		this.unitPrice = pd.getPrice();
		this.inputAmount = true;
		this.productName = pd.getProduct() == null ? "Null" : "Si hay";
		this.productDetail = pd;
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

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public ProductWarehouse getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(ProductWarehouse productDetail) {
		this.productDetail = productDetail;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}
