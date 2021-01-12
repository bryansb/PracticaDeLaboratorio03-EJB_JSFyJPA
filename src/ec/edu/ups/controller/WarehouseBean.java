package ec.edu.ups.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import ec.edu.ups.ejb.CityFacade;
import ec.edu.ups.ejb.WarehouseFacade;
import ec.edu.ups.entities.City;
import ec.edu.ups.entities.ProductWarehouse;
import ec.edu.ups.entities.Warehouse;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class WarehouseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private WarehouseFacade warehouseFacade;

    @EJB
    private CityFacade cityFacade;

    private City city;
    private String address;
    private List<ProductWarehouse> productWarehouseList;
    private List<City> cityList;
    private List<Warehouse> warehouseList;
    
    private int index;

    public WarehouseBean() {
	super();
    }

    @PostConstruct
    public void init() {

//    	for (int i = 0; i < 5; i++) {
//    	    City city = new City();
//    	    Warehouse warehouse = new Warehouse();
//    
//    	    city.setName("Ciudad" + i);
//    	    this.cityFacade.create(city);
//    
//    	    warehouse.setAddress("Dirección" + i);
//    	    warehouse.setCity(city);
//    	    this.warehouseFacade.create(warehouse);
//    	}
	
    	loadCities();
	this.warehouseList = this.warehouseFacade.findAll();   
	
    }

    public String create() {
	Warehouse warehouse = new Warehouse();
	warehouse.setAddress(this.address);
	this.city = cityFacade.read(index);
	warehouse.setCity(this.city);
	
	warehouseFacade.create(warehouse);
	this.warehouseList = this.warehouseFacade.findAll();  
	
	return null;
    }

    public String read() {

	return null;
    }

    public String edit(Warehouse warehouse) {
	warehouse.setEditable(true);
	return null;
    }

    public String save(Warehouse warehouse) {
	warehouseFacade.update(warehouse);
	warehouse.setEditable(false);
	return null;
    }

    public String delete(Warehouse warehouse) {
	warehouseFacade.delete(warehouse);
	this.warehouseList = this.warehouseFacade.findAll();
	return null;
    }

    public List<Warehouse> getWarehouseList() {
	return this.warehouseFacade.findAll();
    }

    public City getCity() {
	return city;
    }

    public void setCity(City city) {
	this.city = city;
    }

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public List<ProductWarehouse> getProductWarehouseList() {
	return productWarehouseList;
    }

    public void setProductWarehouseList(
	    List<ProductWarehouse> productWarehouseList) {
	this.productWarehouseList = productWarehouseList;
    }

    public List<City> getCityList() {
	return cityList;
    }
    
    public void setCityList(List<City> cityList) {
	this.cityList = cityList;
    }
    
    public void loadCities() {
	this.cityList = cityFacade.findAll();
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
