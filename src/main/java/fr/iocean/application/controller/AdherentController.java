package fr.iocean.application.controller;



import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import fr.iocean.application.model.Adherent;
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
	
	

	
	
	
	
	
	

}
