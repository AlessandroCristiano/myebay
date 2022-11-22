package it.prova.myebay.web.controller;


import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.myebay.dto.RuoloDTO;
import it.prova.myebay.dto.UtenteCambiaPasswordDTO;
import it.prova.myebay.dto.UtenteDTO;
import it.prova.myebay.service.UtenteService;
import it.prova.myebay.validation.ValidationNoPassword;
import it.prova.myebay.validation.ValidationWithPassword;

@Controller
@RequestMapping(value = "/account")
public class AccountController {
	
	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("/resettaPassword")
	public String resettaPassword(Model model, RedirectAttributes redirectAttrs,
			HttpServletRequest request) {
		model.addAttribute("show_utente_attr", new UtenteCambiaPasswordDTO());
		return "utente/resetPassword";
	}
	
	@PostMapping("/reset")
	public String reset(@Validated({ ValidationWithPassword.class,
					ValidationNoPassword.class }) @ModelAttribute("show_utente_attr") UtenteCambiaPasswordDTO utenteCambiaPasswordDTO,
			BindingResult result, Model model, RedirectAttributes redirectAttrs, Principal principal) {

		if(!result.hasFieldErrors("vecchiaPassword") && !passwordEncoder.matches(utenteCambiaPasswordDTO.getVecchiaPassword(), utenteService.findByUsername(principal.getName()).getPassword()))
			result.rejectValue("vecchiaPassword", "utente.password.vecchiaPassword");
		
		
		if (!result.hasFieldErrors("nuovaPassword") && !utenteCambiaPasswordDTO.getNuovaPassword().equals(utenteCambiaPasswordDTO.getConfermaPassword()))
			result.rejectValue("confermaPassword", "utente.password.diverse");

		if(result.hasErrors())
			return "utente/resetPassword";
		
		utenteService.cambiaPassword(utenteCambiaPasswordDTO, utenteService.findByUsername(principal.getName()));

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/executeLogout";
	}

}
