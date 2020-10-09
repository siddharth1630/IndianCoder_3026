package com.telusko.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.telusko.entity.AdminLogin;
import com.telusko.entity.Customer;
import com.telusko.entity.CustomerLogin;
import com.telusko.entity.Dealer;
import com.telusko.entity.DealerLogin;
import com.telusko.entity.GasBooking;
import com.telusko.entity.GasConnection;
import com.telusko.service.AdminService;
import com.telusko.service.CustomerService;
import com.telusko.service.DealerService;

@Controller
@SessionAttributes({"session","consumerSession"})	// use session for panCard, email
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}

	@GetMapping("/customerLogin")
	public String customerLogin(Model model) {
		CustomerLogin customerLogin=new CustomerLogin();
		model.addAttribute("customer", customerLogin);
		return "customerLogin";	
	}
	
	@GetMapping("/register")
	public String customerRegister(Model model) {
		Customer customer=new Customer();
		model.addAttribute("customer", customer);
		return "register";
	}
	
	@PostMapping("/registered")
	public String customerRegistered(@Valid @ModelAttribute("customer") Customer customer,
							BindingResult br,Model model,RedirectAttributes attributes) {
		if(br.hasErrors()) {
			return "/register";
		}
		else {
			if(customer.getPassword().equals(customer.getcPassword())) {
				customerService.saveCustomer(customer);
				return "redirect:/customerLogin";
			}
			else {
				attributes.addFlashAttribute("success","password and confirm password doesn't match");
				return "redirect:/register";
			}
		}
	}
	
	@PostMapping("/loggedIn")
	public String customerLogedIn(@Valid @ModelAttribute("customer") CustomerLogin customerLogin,
							BindingResult br,Model model,RedirectAttributes attributes) {
		if(br.hasErrors()) {
			return "customerLogin";
		}
		else {
			boolean chk=customerService.validateCustomer(customerLogin);
			if(chk==false) {
				model.addAttribute("customer", customerLogin);
				model.addAttribute("session", customerLogin.getEmail());	// session for store email 
				return "customer-home";
			}
			else {
				attributes.addFlashAttribute("success","you are not registerd/ wrong password");
				return "redirect:/customerLogin";
			}
		}
	}
	
	@GetMapping("/newConnection")
	public String newConnection(Model model) {
		GasConnection gcon=new GasConnection();
		model.addAttribute("gasCon", gcon);
		return "new-connection";
	}
	
	@PostMapping("/status")
	public String gasConnectioned(@Valid @ModelAttribute("gasCon") GasConnection gascon,
									BindingResult br,Model model) {
		if(br.hasErrors()) {
			return "new-connection";
		}
		else {
			customerService.saveGasConnection(gascon);
			model.addAttribute("gasDetail", gascon);
			return "status";
		}
	}
	
	@GetMapping("/allStatus")
	public String getAllStatus(Model model) {
		String email=(String) model.getAttribute("session");
		List<GasConnection> gasCon=customerService.getAllStatus(email);
		model.addAttribute("allStatus", gasCon);
		
		return "allGasStatus";
	}

	
	
	//========================= CUSTOMER BOOKING CONTROLLER ===========================
	@GetMapping("/newBooking")
	public String newBooking(Model model) {
		String email=(String) model.getAttribute("session");	// getting the customer email
		List<GasConnection> gasConnection=customerService.getAllStatus(email);	// pass it to get the all connection from this email
		GasBooking gBook=new GasBooking(gasConnection);	// we can call the parameterized constructor for intialising the consumerNos with the database value 
		
		model.addAttribute("gasBook", gBook);
		return "new-booking";
	}
	
	@PostMapping("/bookingPage")
	public String bookingStatus(@Valid @ModelAttribute("gasBook") GasBooking gBook,BindingResult br,
							Model model,RedirectAttributes attributes) {
		
		if(br.hasErrors()) {
			attributes.addFlashAttribute("success", "Failed!.. Mobile no. should be 10 digit number");
			return "redirect:/newBooking";
		}
		else {
			if(gBook.getConsumerNo().equals("-")) {
				attributes.addFlashAttribute("success", "INVALID CONSUMER NUMBER");
				return "redirect:/newBooking";
			}
			else {
				customerService.saveGasBooking(gBook);
				model.addAttribute("consumerSession",gBook.getConsumerNo());
				attributes.addFlashAttribute("success", "Your booking is successfully done");	// FOR SHOWING THE SUCCESSFUL MSG IN THE PAGE
				return "redirect:/newBooking";
			}
		}
	}
	
	@GetMapping("/allBookingStatus")
	public String allBookingStatus(Model model) {
		String consumerNo=(String) model.getAttribute("consumerSession");
		String email=(String) model.getAttribute("session");
		
		List<GasBooking> listGasBooking=customerService.getAllBookingList(consumerNo,email);
		model.addAttribute("allBookingStatus", listGasBooking);
		
		return "all-booking-status";
	}
	
	
}
