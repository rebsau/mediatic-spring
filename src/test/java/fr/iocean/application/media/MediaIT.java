package fr.iocean.application.media;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.bind.annotation.RequestParam;

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
	public void testUpdate() throws Exception{
		Media media = mediaService.findMediaById(1l);
		media.setTitre("Tittre");
		
		this.mockMvc.perform(put("/api/medias").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
				.content(jsonHelper.serialize(media))).andExpect(status().isOk());
		this.mockMvc.perform(get("/api/medias/1")).andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void testSearchTriDescById() throws Exception{
		
		this.mockMvc.perform(get("/api/medias")
				.param("page", "0")
				.param("ascend", "false")
				.param("triParam", "id")
				.param("title", "")
				.param("author", "")
				.param("type", ""))
		.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void testSearchTriAsccBytitleSearchByauthor() throws Exception{
		
		this.mockMvc.perform(get("/api/medias")
				.param("page", "0")
				.param("ascend", "true")
				.param("triParam", "titre")
				.param("title", "")
				.param("author", "aa")
				.param("type", ""))
		.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void testSearchTriAsccByIdSearchByTitle() throws Exception{
		
		this.mockMvc.perform(get("/api/medias")
				.param("page", "0")
				.param("ascend", "false")
				.param("triParam", "type")
				.param("title", "ti")
				.param("author", "")
				.param("type", ""))
		.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void testSearchTriDescByIdSearchByType() throws Exception{
		
		this.mockMvc.perform(get("/api/medias")
				.param("page", "0")
				.param("ascend", "false")
				.param("triParam", "id")
				.param("title", "")
				.param("author", "")
				.param("type", "DVD"))
		.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void testSearchTriBy() throws Exception{
		
		this.mockMvc.perform(get("/api/medias")
				.param("page", "0")
				.param("ascend", "false")
				.param("triParam", "emprunt")
				.param("title", "")
				.param("author", "")
				.param("type", ""))
		.andDo(MockMvcResultHandlers.print());
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
