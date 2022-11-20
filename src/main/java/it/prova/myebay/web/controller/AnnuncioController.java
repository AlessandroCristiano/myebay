package it.prova.myebay.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.myebay.dto.AnnuncioDTO;
import it.prova.myebay.dto.CategoriaDTO;
import it.prova.myebay.dto.RuoloDTO;
import it.prova.myebay.dto.UtenteDTO;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.AnnuncioService;
import it.prova.myebay.service.CategoriaService;
import it.prova.myebay.service.UtenteService;
import it.prova.myebay.validation.ValidationNoPassword;
import it.prova.myebay.validation.ValidationWithPassword;

@Controller
@RequestMapping(value = "/annuncio")
public class AnnuncioController {
	
	@Autowired
	private AnnuncioService annuncioService;

	@Autowired
	private CategoriaService categoriaService;
	
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
		model.addAttribute("annunci_list_attribute",AnnuncioDTO.
				createAnnuncioDTOFromModelList(annuncioService.findByExample(annuncioExample), false));
		return "annuncio/list";
	}
	
	@GetMapping("/show/{idAnnuncio}")
	public String showAnnuncio(@PathVariable(required = true) Long idAnnuncio, Model model) {
		Annuncio annuncioModel = annuncioService.caricaSingoloElementoEager(idAnnuncio); //da fare caricamento eager
		AnnuncioDTO result = AnnuncioDTO.buildAnnuncioDTOFromModel(annuncioModel,true);
		model.addAttribute("show_annuncio_attr", result);
		model.addAttribute("utente_annuncio_attr", UtenteDTO.buildUtenteDTOFromModel(utenteService.caricaSingoloUtente(result.getUtente().getId()), false));
		return "annuncio/show";
	}
	
	@GetMapping("/insert")
	public String create(Model model) {
		model.addAttribute("categorie_totali_attr", CategoriaDTO.createCategoriaDTOListFromModelList(categoriaService.listAllElements(), false));
		model.addAttribute("insert_annuncio_attr", new AnnuncioDTO());
		return "annuncio/insert";
	}
	
	// per la validazione devo usare i groups in quanto nella insert devo validare
	// la pwd, nella edit no
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("insert_annuncio_attr") AnnuncioDTO annuncioDTO,@RequestParam(name="utenteId") Long utenteId,
			BindingResult result, Model model, RedirectAttributes redirectAttrs) {

		if (result.hasErrors()) {
			model.addAttribute("show_annuncio_attr", AnnuncioDTO.createAnnuncioDTOFromModelList(annuncioService.listAllElements(), false));
			return "annuncio/insert";
		}
		
		annuncioDTO.setUtente(UtenteDTO.buildUtenteDTOFromModel(utenteService.caricaSingoloUtente(utenteId), true));
		annuncioDTO.setData(new Date());
		annuncioDTO.setAperto(true);
		annuncioService.inserisciNuovo(annuncioDTO.buildAnnuncioModel(true));

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/home";
	}
	
	@PostMapping("/listAnnunciUtente")
	public String insertAnnuncio(@RequestParam(name = "utenteId") Long utenteId
			,Model model) {		
			model.addAttribute("annunci_list_attribute",
					AnnuncioDTO.createAnnuncioDTOFromModelList(annuncioService.FindAllAnnunciById(utenteId), true));
			
		return "annuncio/list";
	}
	
	@GetMapping("/delete/{idAnnuncio}")
	public String delete(@PathVariable(required = true) Long idAnnuncio, Model model, HttpServletRequest request) {
		AnnuncioDTO annuncioDTO = AnnuncioDTO.buildAnnuncioDTOFromModel(annuncioService.caricaSingoloElementoConCategorie(idAnnuncio), true);
		model.addAttribute("delete_annuncio_attr", annuncioDTO);
		

		model.addAttribute("categorie_annuncio_attr", CategoriaDTO
				.createCategoriaDTOListFromModelList(categoriaService.cercaCategorieFromId(annuncioDTO.getCategorie()), true));
		
		return "annuncio/delete";
	}
}
