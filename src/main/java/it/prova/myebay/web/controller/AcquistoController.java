package it.prova.myebay.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.prova.myebay.dto.AcquistoDTO;
import it.prova.myebay.service.AcquistoService;
import it.prova.myebay.service.AnnuncioService;
import it.prova.myebay.service.CategoriaService;
import it.prova.myebay.service.RuoloService;
import it.prova.myebay.service.UtenteService;

@Controller
@RequestMapping(value = "/acquisto")
public class AcquistoController {
	
	@Autowired
	private AnnuncioService annuncioService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private RuoloService ruoloService;
	
	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private AcquistoService acquistoService;
	
	@PostMapping("/list")
	public String insertAnnuncio(@RequestParam(name = "utenteId") Long utenteId
			,Model model) {		
			model.addAttribute("acquisto_list_attribute",
					AcquistoDTO.createAcquistoDTOFromModelList(acquistoService.FindAllAcquistiByIdUtente(utenteId), true));
			
		return "acquisto/list";
	}

}
