package com.sagar.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.razorpay.RazorpayException;
import com.sagar.dto.StudentOrder;
import com.sagar.service.StudentService;

@Controller
public class StudentController {
	
	@Autowired
	private StudentService service;
	@GetMapping("/")
	public String init() {
		return "index";
	}
	
	
	// capture the data
	@PostMapping(value="/create-order",produces= {"application/json"})
	@ResponseBody // there is no required to develop a html file
	public ResponseEntity<StudentOrder> createOrder( @RequestBody StudentOrder stuOrder) throws RazorpayException{// requestBody is used to take the form data in java object....
		
		StudentOrder createdOrder=service.createOrder(stuOrder);
		
		return new ResponseEntity<>(createdOrder,HttpStatus.CREATED);
		
		
	}

	//after payment is success then razorpay redirect the url for showing success acknowledgement.
	@GetMapping("/payment-success")
	public String showSuccessPage(
	    @RequestParam("razorpay_order_id") String orderId,
	    @RequestParam("razorpay_payment_id") String paymentId,
	    @RequestParam("razorpay_signature") String signature,
	    Model model) {

	    model.addAttribute("orderId", orderId);
	    model.addAttribute("paymentId", paymentId);
	    model.addAttribute("signature", signature);

	    return "Success"; // JSP or Thymeleaf view name
	}



// razorpay should call the callback method,after success payment

}


// in method having throws Exception then while call iin other class's method also having the throws exception......