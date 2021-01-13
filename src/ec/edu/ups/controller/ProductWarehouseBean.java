package ec.edu.ups.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import ec.edu.ups.ejb.ProductFacade;
import ec.edu.ups.ejb.ProductWarehouseFacade;
import ec.edu.ups.ejb.WarehouseFacade;
import ec.edu.ups.entities.Product;
import ec.edu.ups.entities.ProductWarehouse;
import ec.edu.ups.entities.Warehouse;


@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class ProductWarehouseBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @EJB
    private ProductWarehouseFacade productWarehouseFacade;
    
    @EJB
    private WarehouseFacade warehouseFacade;
    
    @EJB
    private ProductFacade productFacade;
    
    
    private List<ProductWarehouse> productWarehouseList;
    private List<ProductWarehouse> selectedList;
    private List<Warehouse> warehouseList;
    private List<Product> productList;
    
    private Warehouse warehouse;
    private Product product;
    
    private double price;
    private int index;
    private int stock;
    
    public ProductWarehouseBean() {
	super();
    }
    
    @PostConstruct
    public void init() {
	loadProducts();
	loadProductWarehouses();
    }
    
    public void getProductsByWarehouse() {
	
    }
    
    public void loadProductWarehouses() {
	this.productWarehouseList = this.productWarehouseFacade.findAll();
    }
    
    public void loadProducts() {
	this.productList = this.productFacade.findAll();
    }
    
    public String create(Warehouse warehouse) {
	ProductWarehouse productWarehouse = new ProductWarehouse();
	productWarehouse.setPrice(this.price);
	productWarehouse.setStock(this.stock);
	
	this.product = productFacade.read(index);
	productWarehouse.setProduct(this.product);
	
	this.warehouse = warehouse;
	productWarehouse.setWarehouse(this.warehouse);
	
	productWarehouseFacade.create(productWarehouse);
	
	loadProductWarehouses();
	getProductsByWarehouse();
	return null;
    }
    
    public String read() {

	return null;
    }

    public String edit(ProductWarehouse productWarehouse) {
	productWarehouse.setEditable(true);
	return null;
    }

    public String save(ProductWarehouse productWarehouse) {
	productWarehouseFacade.update(productWarehouse);
	productWarehouse.setEditable(false);
	return null;
    }

    public String delete(ProductWarehouse productWarehouse) {
	productWarehouse.setDeleted(true);
	productWarehouseFacade.update(productWarehouse);
	loadProductWarehouses();
	return null;
    }
    
    public String restore(ProductWarehouse productWarehouse) {
	productWarehouse.setDeleted(false);
	productWarehouseFacade.update(productWarehouse);
	loadProductWarehouses();
	return null;
    }
    
    public List<Warehouse> getWarehouseList() {
        return warehouseList;
    }
    
    public void setWarehouseList(List<Warehouse> warehouseList) {
        this.warehouseList = warehouseList;
    }
    
    public List<Product> getProductList() {
        return productList;
    }
    
    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
    
    public List<ProductWarehouse> getProductWarehouseList() {
        return productWarehouseList;
    }

    public void setProductWarehouseList(
    	List<ProductWarehouse> productWarehouseList) {
        this.productWarehouseList = productWarehouseList;
    }

    public List<ProductWarehouse> getSelectedList() {
        return selectedList;
    }

    public void setSelectedList(List<ProductWarehouse> selectedList) {
        this.selectedList = selectedList;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }
    
    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
    
    public Product getProduct() {
        return product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public int getIndex() {
        return index;
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
    
    public int getStock() {
        return stock;
    }
    
    public void setStock(int stock) {
        this.stock = stock;
    } 

}
