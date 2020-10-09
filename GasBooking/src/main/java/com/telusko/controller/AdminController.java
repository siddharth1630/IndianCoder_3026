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
import com.telusko.entity.Dealer;
import com.telusko.entity.GasBooking;
import com.telusko.entity.GasConnection;
import com.telusko.service.AdminService;

@Controller
@SessionAttributes({"session","consumerSession"})	// use session for panCard, email
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/adminLogin")
	public String adminLogin(Model model) {
		AdminLogin admin=new AdminLogin();
		model.addAttribute("adminLogin",admin);
		
		return "adminLogin";
	}
	
	@PostMapping("/adminHome")
	public String adminHome(@Valid @ModelAttribute("adminLogin") AdminLogin admin,BindingResult br,
						Model model,RedirectAttributes attributes) {
		if(br.hasErrors()) {
			return "adminLogin";
		}
		else {
			String email=admin.getEmail();
			String pass=admin.getPassword();
			if(email.equals("sid@gmail.com") && pass.equals("hellosid")) {
				model.addAttribute("adminDetail",admin);
				model.addAttribute("session",email);		// inserting the admin email in session
				return "admin-home";
			}
			else {
				attributes.addFlashAttribute("success","Email and passwrod doesn't match");
				return "redirect:/adminLogin";
			}
		}
	}
	
	@GetMapping("/totalDealer")
	public String totalDealer(Model model) {
		List<Dealer> dealer=adminService.getAllDealerDetail();
		model.addAttribute("dealerDetail", dealer);
		return "total-dealer";
	}
	
	@GetMapping("/approveDealer")
	public String approveDealer(@RequestParam("id") int id,Model model,RedirectAttributes attributes) {
		adminService.approveDealer(id);
		attributes.addFlashAttribute("success","Successfully approved the Dealer");
		return "redirect:/totalDealer";
	}
	
	@GetMapping("/deleteDealer")
	public String deleteDealer(@RequestParam("id") int id,Model model,RedirectAttributes attributes) {
		adminService.deleteDealer(id);
		attributes.addFlashAttribute("success","You can Deleted the dealer");
		return "redirect:/totalDealer";
	}
	
	@GetMapping("/totalBooking")
	public String totalBooking(Model model) {
		List<GasBooking> booking=adminService.getAllBookingDetail();
		model.addAttribute("bookingDetail", booking);
		return "total-booking";
	}
	
	@GetMapping("/totalConnection")
	public String totalConnection(Model model) {
		List<GasConnection> connection=adminService.getAllConnectionDetail();
		model.addAttribute("connectionDetail", connection);
		return "total-connection";
	}
	
	@GetMapping("/deleteConnection")
	public String deleteConnection(@RequestParam("id") int id,Model model,RedirectAttributes attributes) {
		adminService.deleteConnection(id);
		attributes.addFlashAttribute("success","Connection is deleted");
		return "redirect:/totalConnection";
	}

}
