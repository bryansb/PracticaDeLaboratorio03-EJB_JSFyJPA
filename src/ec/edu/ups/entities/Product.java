package ec.edu.ups.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Products
 *
 */
@Entity
@Table(name="PRODUCTS")

public class Product implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pro_id")
	private int id;
	
	@Column(name = "pro_name", length = 255, nullable = false)
	private String name;
	
	@Column(name = "pro_stock")
	private int stock;
	
	@Column(name = "pro_price", precision = 10, scale = 2)
	private double price;
	
	@Column(name = "pro_deleted", columnDefinition = "BOOLEAN DEFAULT 0")
	private boolean deleted;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
	private List<Warehouse> warehouses = new ArrayList<Warehouse>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
	private List<BillDetail> billDetails = new ArrayList<BillDetail>();
	
	@ManyToOne
	@JoinColumn
	private Category category;

	public Product() {
		super();
	}

	public int getId() {
	    return id;
	}

	public void setId(int id) {
	    this.id = id;
	}

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}

	public int getStock() {
	    return stock;
	}

	public void setStock(int stock) {
	    this.stock = stock;
	}

	public double getPrice() {
	    return price;
	}

	public void setPrice(double price) {
	    this.price = price;
	}

	public boolean isDeleted() {
	    return deleted;
	}

	public void setDeleted(boolean deleted) {
	    this.deleted = deleted;
	}

	public List<Warehouse> getWarehouses() {
	    return warehouses;
	}

	public void setWarehouses(List<Warehouse> warehouses) {
	    this.warehouses = warehouses;
	}

	public List<BillDetail> getBillDetails() {
	    return billDetails;
	}

	public void setBillDetails(List<BillDetail> billDetails) {
	    this.billDetails = billDetails;
	}

	public Category getCategory() {
	    return category;
	}

	public void setCategory(Category category) {
	    this.category = category;
	}

	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result
		    + ((billDetails == null) ? 0 : billDetails.hashCode());
	    result = prime * result
		    + ((category == null) ? 0 : category.hashCode());
	    result = prime * result + (deleted ? 1231 : 1237);
	    result = prime * result + id;
	    result = prime * result + ((name == null) ? 0 : name.hashCode());
	    long temp;
	    temp = Double.doubleToLongBits(price);
	    result = prime * result + (int) (temp ^ (temp >>> 32));
	    result = prime * result + stock;
	    result = prime * result
		    + ((warehouses == null) ? 0 : warehouses.hashCode());
	    return result;
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
		return true;
	    if (obj == null)
		return false;
	    if (getClass() != obj.getClass())
		return false;
	    Product other = (Product) obj;
	    if (billDetails == null) {
		if (other.billDetails != null)
		    return false;
	    } else if (!billDetails.equals(other.billDetails))
		return false;
	    if (category == null) {
		if (other.category != null)
		    return false;
	    } else if (!category.equals(other.category))
		return false;
	    if (deleted != other.deleted)
		return false;
	    if (id != other.id)
		return false;
	    if (name == null) {
		if (other.name != null)
		    return false;
	    } else if (!name.equals(other.name))
		return false;
	    if (Double.doubleToLongBits(price) != Double
		    .doubleToLongBits(other.price))
		return false;
	    if (stock != other.stock)
		return false;
	    if (warehouses == null) {
		if (other.warehouses != null)
		    return false;
	    } else if (!warehouses.equals(other.warehouses))
		return false;
	    return true;
	}

	@Override
	public String toString() {
	    return "Product [id=" + id + ", name=" + name + ", stock=" + stock
		    + ", price=" + price + ", deleted=" + deleted
		    + ", warehouses=" + warehouses + ", billDetails="
		    + billDetails + ", category=" + category + "]";
	}
   
}
