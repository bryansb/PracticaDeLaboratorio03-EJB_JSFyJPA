package ec.edu.ups.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Warehouse
 *
 */
@Entity
@Table(name="WAREHOUSES")

public class Warehouse implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="war_id")
	private int id;
	
	@Column(name="war_address", length=255, nullable=false)
	private String address;
	
	@ManyToOne
	@JoinColumn
	private City city;
	
	@ManyToOne
	@JoinColumn
	private Product product;

	public Warehouse() {
		super();
	}

	public int getId() {
	    return id;
	}

	public void setId(int id) {
	    this.id = id;
	}

	public String getAddress() {
	    return address;
	}

	public void setAddress(String address) {
	    this.address = address;
	}

	public City getCity() {
	    return city;
	}

	public void setCity(City city) {
	    this.city = city;
	}

	public Product getProduct() {
	    return product;
	}

	public void setProduct(Product product) {
	    this.product = product;
	}

	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result
		    + ((address == null) ? 0 : address.hashCode());
	    result = prime * result + ((city == null) ? 0 : city.hashCode());
	    result = prime * result + id;
	    result = prime * result
		    + ((product == null) ? 0 : product.hashCode());
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
	    Warehouse other = (Warehouse) obj;
	    if (address == null) {
		if (other.address != null)
		    return false;
	    } else if (!address.equals(other.address))
		return false;
	    if (city == null) {
		if (other.city != null)
		    return false;
	    } else if (!city.equals(other.city))
		return false;
	    if (id != other.id)
		return false;
	    if (product == null) {
		if (other.product != null)
		    return false;
	    } else if (!product.equals(other.product))
		return false;
	    return true;
	}

	@Override
	public String toString() {
	    return "Warehouse [id=" + id + ", address=" + address + ", city="
		    + city + ", product=" + product + "]";
	}
   
}
