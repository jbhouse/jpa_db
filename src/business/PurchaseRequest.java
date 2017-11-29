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
public class PurchaseRequest implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="UserID")
	private User user;
	@ManyToOne
	@JoinColumn(name="StatusID")
	private Status status;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="PurchaseRequestID")
	private List<PurchaseRequestLineItem> lineItems;
	private String Description;
    private String Justification;
    private String DeliveryMode;
    private String ReasonForRejection;
	private String DateNeeded;
    private Boolean IsActive;
    
	public PurchaseRequest() {}
	
	public void setlineItems(List<PurchaseRequestLineItem> lis) {
		this.lineItems = lis;
	}
	
	public List<PurchaseRequestLineItem> getlineItems(){
		return this.lineItems;
	}
		
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getJustification() {
		return Justification;
	}

	public void setJustification(String justification) {
		Justification = justification;
	}

	public String getDeliveryMode() {
		return DeliveryMode;
	}

	public void setDeliveryMode(String deliveryMode) {
		DeliveryMode = deliveryMode;
	}

	public String getReasonForRejection() {
		return ReasonForRejection;
	}

	public void setReasonForRejection(String reasonForRejection) {
		ReasonForRejection = reasonForRejection;
	}

	public Boolean getIsActive() {
		return IsActive;
	}

	public void setIsActive(Boolean isActive) {
		IsActive = isActive;
	}
	
    public String getDateNeeded() {
		return DateNeeded;
	}

	public void setDateNeeded(String dateNeeded) {
		DateNeeded = dateNeeded;
	}

}
