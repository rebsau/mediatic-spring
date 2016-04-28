package fr.iocean.application.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import fr.iocean.application.model.Emprunt;
import fr.iocean.application.service.EmpruntService;

public class EmpruntController {

	@Autowired
	EmpruntService empruntService;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody @Valid Emprunt emprunt){
		empruntService.save(emprunt);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public void update(@RequestBody @Valid Emprunt emprunt){
		empruntService.save(emprunt);
	}
	
	@RequestMapping(value = "{mediaId}", method = RequestMethod.GET)
	public PageImpl<Emprunt> findListEmpruntForMedia(@RequestParam int page,@RequestParam boolean ascend,@RequestParam String triParam,
			@PathVariable Long mediaId){
		return empruntService.searchListEmpruntByMedia(page, ascend, triParam, mediaId);
	}
	
	@RequestMapping(value = "{adherentId}", method = RequestMethod.GET)
	public PageImpl<Emprunt> findListEmpruntForAdherent(@RequestParam int page,@RequestParam boolean ascend,@RequestParam String triParam,
			@PathVariable Long adherentId){
		return empruntService.searchListEmpruntByAdherent(page, ascend, triParam, adherentId);
	}
}
