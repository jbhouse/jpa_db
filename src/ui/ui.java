package ui;

import java.sql.Timestamp;
import java.util.List;

import business.*;
import db.ProductDB;
import db.PurchaseRequestDB;
import db.PurchaseRequestLineItemDB;
import db.StatusDB;
import db.UserDB;
import db.VendorDB;

public class ui {

	public static void main(String[] args) {
		
		
		
		printUsers();
		
		User u = UserDB.getUserById(Console.getInt("user id to retrieve: "));
		System.out.println("User details: "+u);
		
		System.out.println("purchase requests for this user");
		for(PurchaseRequest pr : u.getpurchaserequests()) {
			System.out.println(pr);
			for(PurchaseRequestLineItem prli: pr.getlineItems()) {
				System.out.println("\t"+prli.getProduct()+" quantity: "+prli.getQuantity());
			}
		}
		
		System.out.println("\nVendors\n");
		
		for(Vendor v : VendorDB.getAllVendors()) {
			System.out.println(v);
		}
		
		System.out.println("Let's make a new purchase request: ");
		PurchaseRequest purReq = new PurchaseRequest();
		purReq.setUser(u);
		purReq.setStatus(StatusDB.getStatusById(1));
		purReq.setDescription(Console.getString("add a description: "));
		purReq.setJustification(Console.getString("why do you need this?: "));
		purReq.setDeliveryMode(Console.getString("delivery mode: "));
		purReq.setDateNeeded(Console.getString("When do you need this by? (yyyy-mm-dd): "));
		purReq.setIsActive(true);
		PurchaseRequestDB.addPurchaseRequest(purReq);
		
		PurchaseRequestLineItem newPRLI = new PurchaseRequestLineItem();
		newPRLI.setPurchaseRequest(purReq);
		for(Product p : ProductDB.getAll()) {
			System.out.println(p);
		}
		newPRLI.setProduct(ProductDB.getProductById(Console.getInt("Id of the product you want to add: ")));
		newPRLI.setQuantity(Console.getInt("Quantity needed: "));
		newPRLI.setIsActive(true);
		newPRLI.setUpdatedByUser(u.getId());
		PurchaseRequestLineItemDB.addPurchaseRequestLineItem(newPRLI);
//		Vendor nv = new Vendor();
//		nv.setAddress(Console.getString("address for new vendor: "));
//		nv.setCity(Console.getString("city for new vendor: "));
//		nv.setCode(Console.getString("code for new vendor: "));
//		nv.setEmail(Console.getString("email for new vendor: "));
//		nv.setIsActive(true);
//		nv.setIsPreApproved(false);
//		nv.setName(Console.getString("name for new vendor: "));
//		nv.setPhone(Console.getString("phone for new vendor: "));
//		nv.setState(Console.getString("state for new vendor: "));
//		nv.setUpDatedByUser(u.getId());
//		nv.setZip(Console.getString("zip code for new vendor: "));
//		
//		VendorDB.addVendor(nv);
//		System.out.println();
//		for(Vendor v : VendorDB.getAllVendors()) {
//			System.out.println(v.getAddress()+" "+v.getCity()+" "+v.getState());
//		}
		
		int vendorId  = Console.getInt("\nVendor to retrieve by id: ");
		Vendor v = VendorDB.getVendorById(vendorId);
		System.out.println("Vendor info: "+v);
		int prId = Console.getInt("purchase request id: ");
		PurchaseRequest pr = PurchaseRequestDB.getPurchaseRequestById(prId);
		System.out.println("purchase request info: "+pr);
		List<PurchaseRequestLineItem> prlis = pr.getlineItems();
		for(PurchaseRequestLineItem p : prlis) {
			System.out.println("\t"+p.getProduct()+" quantity "+p.getQuantity());
		}	
		System.out.println("\nLet's create a new user");
		addUser();
		
		UserDB.deleteUser(Console.getInt("delete a user by id: "));
		printUsers();
		User updatingUser = UserDB.getUserById(Console.getInt("Choose user to update by id: "));
		updatingUser.setFirstName(Console.getString("Change their first name to: "));
		UserDB.update(updatingUser);
		printUsers();
		
	}
	
	private static void addUser() {
		String firstName = Console.getString("first name: ");
		String lastName = Console.getString("last name: ");
		String userName = Console.getString("user name: ");
		String password = Console.getString("password: ");
		String phone = Console.getString("phone number: ");
		String email = Console.getString("email: ");
		User u = new User();
		u.setFirstName(firstName);
		u.setLastName(lastName);
		u.setUserName(userName);
		u.setPassword(password);
		u.setPhone(phone);
		u.setEmail(email);
		u.setIsReviewer(false);
		u.setIsAdmin(false);
		u.setIsActive(true);
		u.setDateCreated(new Timestamp(System.currentTimeMillis()));
		u.setDateUpdated(new Timestamp(System.currentTimeMillis()));
		User usr = UserDB.addUser(u);
		
		System.out.println();
		System.out.println(usr.getId());
	}
	
	private static void printUsers() {
		for(User u : UserDB.getAll()) {
			System.out.println(u.getId()+" "+u.getFirstName()+" "+u.getLastName());
		}
	}

}
