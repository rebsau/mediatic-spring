package fr.iocean.application.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.iocean.application.model.Media;
import fr.iocean.application.service.MediaService;

@RestController
@RequestMapping("/api/medias")
@Transactional
public class MediaController {

	@Autowired
	private MediaService mediaService;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody @Valid Media media){
		mediaService.save(media);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public void update(@RequestBody @Valid Media media){
		mediaService.save(media);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public PageImpl<Media> searchMedias(@RequestParam int page,@RequestParam boolean ascend,@RequestParam String triParam,
			@RequestParam String title,@RequestParam String author,@RequestParam String type){
		return mediaService.searchMedias(page, ascend, triParam, title, author, type);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public Media findMedia(@PathVariable Long id){
		return mediaService.findMediaById(id);
	}
}
