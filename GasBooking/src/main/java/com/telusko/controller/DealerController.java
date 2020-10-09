package com.telusko.controller;

import java.util.List;

import javax.persistence.NoResultException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.telusko.entity.Dealer;
import com.telusko.entity.DealerLogin;
import com.telusko.entity.GasConnection;
import com.telusko.service.CustomerService;
import com.telusko.service.DealerService;

@Controller
@SessionAttributes({"session","consumerSession"})	// use session for panCard, email
public class DealerController {
	
	@Autowired
	private DealerService dealerService;
	
	@Autowired
	private CustomerService customerService;
	

	@GetMapping("/dealerLogin")
	public String dealearLogin(Model model) {
		DealerLogin dealerLogin=new DealerLogin();
		model.addAttribute("dealer", dealerLogin);
		return "dealerLogin";	
	}
	
	@GetMapping("/dealerRegister")
	public String dealerRegister(Model model) {
		Dealer dealer=new Dealer();
		model.addAttribute("dealer", dealer);
		return "dealerRegister";
	}
	
	@PostMapping("/dealerRegistered")
	public String dealerRegistered(@Valid @ModelAttribute("dealer") Dealer dealer,BindingResult br,
							Model model,RedirectAttributes attributes) {
		if(br.hasErrors()) {
			return "/dealerRegister";
		}
		else {
			String panCard=dealer.getPanCard();
			try {
				dealerService.getDistributor(panCard);
				attributes.addFlashAttribute("success","this pan card is already registerd");
				return "redirect:/dealerRegister";			
			}
			catch(NoResultException nre) {
				if(dealer.getPassword().equals(dealer.getcPassword())) {
					dealerService.saveDealer(dealer);
					return "redirect:/dealerLogin";
				}
				else {
					attributes.addFlashAttribute("success","password and confirm password must be same");
					return "redirect:/dealerRegister";
				}
				
			}
		}	
	}
	
	
	@PostMapping("/dealerLoggedIn")
	public String dealerLogedIn(@Valid @ModelAttribute("dealer") DealerLogin dealerLogin,
							BindingResult br,Model model,RedirectAttributes attributes) {
		
		if(br.hasErrors()) {
			return "dealerLogin";
		}
		else {
			boolean chk=dealerService.validateDealer(dealerLogin);
			if(chk==false) {
				model.addAttribute("customer", dealerLogin);
				model.addAttribute("session", dealerLogin.getPanCard());	// session for panCard
				return "dealer-home";
			}
			else {
				attributes.addFlashAttribute("success","your id is not approved by admin/you entered a wrong password");
				return "redirect:/dealerLogin";
			}
		}
	}
	
	@GetMapping("/newConnectionDetail")	// may be change the  customer service to dealer service
	public String bookingDetail(Model model) {
		String panCard=(String) model.getAttribute("session");	// session for getting panCard  value
		String distributor=dealerService.getDistributor(panCard);
		List<GasConnection> gasDetail=customerService.getAllDetail(distributor);
		model.addAttribute("gasDetail", gasDetail);
		return "new-connection-detail";
	}
	
	//========================= GAS APPROVAL/REJECTION CONTROLLER ========================================
	
		@GetMapping("/approveGas")
		public String approveGasConnection(@RequestParam("id") int id,Model model) {
			dealerService.changeStatus(id);
			return "redirect:/newConnectionDetail";
		}
		
		@GetMapping("/rejectGas")
		public String rejectGasConnection(@RequestParam("id") int id,Model model) {
			dealerService.RejectStatus(id);
			return "redirect:/newConnectionDetail";
		}
		
		//===================== DEALER BOOKING REALATED QUERY ======================
		
		@GetMapping("/dealerBookingApproval")
		public String dealerBookingApproval(Model model) {
			String panCard=(String) model.getAttribute("session");	
			String distributor=dealerService.getDistributor(panCard);
			
			List list=dealerService.getPendingBookingRequest(distributor);
			model.addAttribute("pendingReq",list);
			return "dealer-booking-approval";
		}
		
		@GetMapping("/approveBookingGas")
		public String approveBookingGasConnection(@RequestParam("id") int id,Model model,RedirectAttributes attributes) {
			dealerService.changeBookingStatus(id);
			attributes.addFlashAttribute("success","Successfully approved the connection");
			return "redirect:/dealerBookingApproval";
		}
		
		@GetMapping("/rejectBookingGas")
		public String rejectBookingGasConnection(@RequestParam("id") int id,Model model,RedirectAttributes attributes) {
			dealerService.RejectBookingStatus(id);
			attributes.addFlashAttribute("success","You can rejected the connection");
			return "redirect:/dealerBookingApproval";
		}
		
		@GetMapping("/dealerBack")
		public String dealerBack() {
			return "redirect:/dealerBookingApproval";
		}
		
		@GetMapping("/bookingBack")
		public String bookingBack() {
			return "redirect:/newConnectionDetail";
		}
			
	

}
