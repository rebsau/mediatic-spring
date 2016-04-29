package fr.iocean.application.controller;



import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import fr.iocean.application.model.Adherent;
import fr.iocean.application.model.Media;
import fr.iocean.application.service.AdherentService;


@RestController
@RequestMapping("/api/adherent")
@Transactional
public class AdherentController {
	
	
	@Autowired
	private AdherentService adherentService;
	
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public Adherent findById(@PathVariable Long id) {
		return adherentService.findById(id);
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody @Valid Adherent adherent) {
		adherentService.create(adherent);
	}
	
	
	@RequestMapping(method = RequestMethod.PUT)
	public void update(@RequestBody @Valid Adherent adherent) {
		adherentService.update(adherent);
	}
	
	@RequestMapping( method = RequestMethod.GET)
	public PageImpl<Adherent> searchAdherents(@RequestParam int page,@RequestParam boolean ascend,@RequestParam String triParam,
			@RequestParam Long id,@RequestParam String nom){
		System.out.println("controller");
		return adherentService.searchAdherents(page, ascend, triParam, id, nom);
	}
	
	@RequestMapping(value ="/allByName",method = RequestMethod.GET)
	public List<Adherent> findAllByName(@RequestParam String name){
		return adherentService.searchAdherentsByName(name);
	}
	
	

	
	
	
	
	
	

}
