package fr.iocean.application.media;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import fr.iocean.application.IntegrationTest;
import fr.iocean.application.model.Media;
import fr.iocean.application.service.MediaService;

public class MediaIT extends IntegrationTest{

	@Autowired
	MediaService mediaService;
	
	@Test	
	public void testCreate() throws Exception{
		Media u = new Media("myTitre", "monAuteur", Media.Type.Livre);
		
		this.mockMvc.perform(post("/api/medias").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
				.content(jsonHelper.serialize(u))).andExpect(status().isCreated());
	}
	
	@Test
	public void testUpdate(){
	}
	
	@Test
	public void testSearch(){
		mediaService.findMediaById(1l);
	}
	
	@Test 
	public void testFindMedia() throws Exception{
		this.mockMvc.perform(get("/api/medias/1")).andDo(MockMvcResultHandlers.print())
			.andExpect(jsonPath("$.id").value(1))
			.andExpect(jsonPath("$.titre").value("tt"))
			.andExpect(jsonPath("$.emprunt.id").value(11))
			.andExpect(jsonPath("$.emprunt.adherent.nom").value("kholladi"))
			.andExpect(jsonPath("$.emprunt.adherent.prenom").value("zino"));		
	}
}
