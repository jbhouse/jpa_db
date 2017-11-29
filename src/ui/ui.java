package ui;

import java.sql.Timestamp;
import java.util.List;

import business.*;
import db.PurchaseRequestDB;
import db.UserDB;
import db.VendorDB;

public class ui {

	public static void main(String[] args) {
		
		printUsers();
		
		User u = UserDB.getUserById(Console.getInt("user id to retrieve: "));
		System.out.println("User details: "+u.getUserName()+" "+u.getEmail()+" "+u.getFirstName()+" "+u.getLastName()+" "+u.getPhone()+" "+u.getDateCreated());
		
		System.out.println("purchase requests for this user");
		for(PurchaseRequest pr : u.getpurchaserequests()) {
			System.out.println("id: "+pr.getId()+" description: "+pr.getDescription());
			for(PurchaseRequestLineItem prli: pr.getlineItems()) {
				System.out.println("\t"+prli.getProduct().getName());
			}
		}
		
		System.out.println("\nVendors\n");
		
		for(Vendor v : VendorDB.getAllVendors()) {
			System.out.println(v.getAddress()+" "+v.getCity()+" "+v.getState());
		}
		
		Vendor nv = new Vendor();
		nv.setAddress(Console.getString("address for new vendor: "));
		nv.setCity(Console.getString("city for new vendor: "));
		nv.setCode(Console.getString("code for new vendor: "));
		nv.setEmail(Console.getString("email for new vendor: "));
		nv.setIsActive(true);
		nv.setIsPreApproved(false);
		nv.setName(Console.getString("name for new vendor: "));
		nv.setPhone(Console.getString("phone for new vendor: "));
		nv.setState(Console.getString("state for new vendor: "));
		nv.setUpDatedByUser(u.getId());
		nv.setZip(Console.getString("zip code for new vendor: "));
		
		VendorDB.addVendor(nv);
		System.out.println();
		for(Vendor v : VendorDB.getAllVendors()) {
			System.out.println(v.getAddress()+" "+v.getCity()+" "+v.getState());
		}
		
		int vendorId  = Console.getInt("\nVendor to retrieve by id: ");
		Vendor v = VendorDB.getVendorById(vendorId);
		System.out.println("Vendor info: "+v.getName()+" "+v.getAddress()+" "+v.getCity()+" "+v.getState());
		int prId = Console.getInt("purchase request id: ");
		PurchaseRequest pr = PurchaseRequestDB.getPurchaseRequestById(prId);
		System.out.println("purchase request info: "+pr.getDescription()+" "+pr.getDateNeeded()+" "+pr.getDeliveryMode()+" user who created this pr: "+pr.getUser().getFirstName()+" "+pr.getUser().getLastName()+" pr status: "+pr.getStatus().getDescription());
		List<PurchaseRequestLineItem> prlis = pr.getlineItems();
		for(PurchaseRequestLineItem p : prlis) {
			System.out.println("\t"+p.getProduct().getName());
		}	
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
		if (UserDB.addUser(u)) {
			System.out.println("added the user successfully");
		}
		System.out.println();
	}
	
	private static void printUsers() {
		for(User u : UserDB.getAll()) {
			System.out.println(u.getId()+" "+u.getFirstName()+" "+u.getLastName());
		}
	}

}
