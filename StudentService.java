package com.sagar.service;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.sagar.Repo.StudentOrderRepo;
import com.sagar.dto.StudentOrder;

@Service // it is a springbean class
public class StudentService {
	
	@Autowired
	private StudentOrderRepo studentRepo;
	
	// to communicate with razorpay key and key secret
	
	@Value("${razorpay.key.id}")// field injection ,from the application.properties file and also use the key for reading
	private String razorpayKey;
	
	@Value("${razorpay.secret.key}")
	private String razorpaySecret;
	
	
	
	
	//to communicate with sbapplication to razorpayApi ,client is an object
			private RazorpayClient client; 
	//method 
	public StudentOrder createOrder(StudentOrder stuOrder) throws RazorpayException {
		
		
		JSONObject orderReq=new JSONObject(); // for send the order as parameter in clinet.order.cretae().
		orderReq.put("amount",stuOrder.getAmount()*100);// amount in paisa
		orderReq.put("currency", "INR");
		orderReq.put("receipt",stuOrder.getEmail());
		
		this.client=new RazorpayClient(razorpayKey,razorpaySecret); // this client has access the razorpay account by key and secret
		
		// create order in razorpay,create a predefine class for crerate the order,it create the orderId and show the status.
	Order razorpayOrder=	client.orders.create(orderReq);// order is the predefine claas of razorpay API.//orderReq is the JSON object send to the razorpay
		
	
	System.out.print(razorpayOrder);// only show in console...
	stuOrder.setRazorpayOrderid(razorpayOrder.get("id"));// this id from razorpay website
	stuOrder.setOrderStatus(razorpayOrder.get("status"));
	
	studentRepo.save(stuOrder);// save in db
	
	
	return stuOrder;
		
	}// when student submit the form then this method is called and this method create order in razorpay website

	
	
	/*
	 * Summary:
responsePayLoad = the JSON map sent from frontend after Razorpay success.

It helps your backend locate the order and mark it as paid.

You can also use it to verify the signature for security.

Example Content of responsePayLoad:
java
Copy code
{
  "razorpay_order_id": "order_Nxabc12345678",
  "razorpay_payment_id": "pay_Nxxyzz89012345",
  "razorpay_signature": "fakesignature123abc"
}*/

}
