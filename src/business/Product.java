package business;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class Product implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="ProductID")
	private List<PurchaseRequestLineItem> purchaserequestlineitems;
	@ManyToOne
	@JoinColumn(name="VendorID")
	private Vendor vendor;
	private double price;
	private String PartNumber;
    private String Name;
    private String Unit;
    private String PhotoPath;
    private Boolean IsActive;
	
	public Product() {}
	
	public List<PurchaseRequestLineItem> getpurchaserequestlineitems(){
		return this.purchaserequestlineitems;
	}
	
	public void setpurchaserequestlineitems(List<PurchaseRequestLineItem> prs) {
		this.purchaserequestlineitems = prs;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPartNumber() {
		return PartNumber;
	}

	public void setPartNumber(String partNumber) {
		PartNumber = partNumber;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getUnit() {
		return Unit;
	}

	public void setUnit(String unit) {
		Unit = unit;
	}

	public String getPhotoPath() {
		return PhotoPath;
	}

	public void setPhotoPath(String photoPath) {
		PhotoPath = photoPath;
	}

	public Boolean getIsActive() {
		return IsActive;
	}

	public void setIsActive(Boolean isActive) {
		IsActive = isActive;
	}

	@Override
	public String toString() {
		return "Product [id=" + id
				+ ", price=" + price + ", PartNumber=" + PartNumber + ", Name=" + Name + ", Unit=" + Unit
				+ ", PhotoPath=" + PhotoPath + ", IsActive=" + IsActive + "]";
	}

	
	
}
