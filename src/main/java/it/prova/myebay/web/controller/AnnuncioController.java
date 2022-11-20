package it.prova.myebay.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.prova.myebay.dto.AnnuncioDTO;
import it.prova.myebay.dto.RuoloDTO;
import it.prova.myebay.dto.UtenteDTO;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.AnnuncioService;
import it.prova.myebay.service.CategoriaService;
import it.prova.myebay.service.UtenteService;

@Controller
@RequestMapping(value = "/annuncio")
public class AnnuncioController {
	
	@Autowired
	private AnnuncioService annuncioService;

//	@Autowired
//	private CategoriaService categoriaService;
	
	@Autowired
	private UtenteService utenteService;
	
	@GetMapping
	public ModelAndView listAllAnnunci() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("annunci_list_attribute",
				AnnuncioDTO.createAnnuncioDTOFromModelList(annuncioService.listAllElements(), false));
		mv.setViewName("annuncio/list");
		return mv;
	}
	
	@PostMapping("/list")
	public String listAnnunci(HttpServletRequest request, Annuncio annuncioExample, ModelMap model) {	
		UtenteDTO utente = (UtenteDTO) request.getSession().getAttribute("userInfo");
		List<Annuncio> annunci = new ArrayList<>();
		
		if(utente!=null && utente.getId()!=null) {
			annuncioExample.setUtenteInserimento(utente.buildUtenteModel(false));
			annunci= annuncioService.findByExampleEager(annuncioExample);
		}else{
			annunci= annuncioService.findByExample(annuncioExample);
		}
		
		model.addAttribute("annunci_list_attribute",
				AnnuncioDTO.createAnnuncioDTOFromModelList(annunci, false));
		return "annuncio/list";
	}
	
	@GetMapping("/show/{idAnnuncio}")
	public String showAnnuncio(@PathVariable(required = true) Long idAnnuncio, Model model) {
		Annuncio annuncioModel = annuncioService.caricaSingoloElementoEager(idAnnuncio); //da fare caricamento eager
		AnnuncioDTO result = AnnuncioDTO.buildAnnuncioDTOFromModel(annuncioModel,true);
		model.addAttribute("show_annuncio_attr", result);
		model.addAttribute("utente_annuncio_attr", UtenteDTO.buildUtenteDTOFromModel(utenteService.caricaSingoloUtente(result.getUtenti().getId()), false));
		return "annuncio/show";
	}
}
