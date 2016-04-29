package fr.iocean.application.emprunt;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;

import fr.iocean.application.IntegrationTest;
import fr.iocean.application.model.Adherent;
import fr.iocean.application.model.Emprunt;
import fr.iocean.application.model.Media;
import fr.iocean.application.repository.AdherentRepository;
import fr.iocean.application.repository.EmpruntRepository;
import fr.iocean.application.repository.MediaRepository;

public class EmpruntRepositoryIT extends IntegrationTest {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	EmpruntRepository empruntRepository;
	
	@Autowired
	MediaRepository mediaRepository;
	
	@Autowired
	AdherentRepository adhRepository;
	
	@Test
	public void testFindEmpruntForMedia() throws ParseException{
		
		Pageable pageable = new PageRequest(0, 10);
		Page<Emprunt> page = empruntRepository.findListEmpruntByMediaId(pageable, 1l);
		
		List<Emprunt> list = page.getContent();
		
		//Assert.assertEquals(list.get(0).getDateEmprunt(), sdf.parse("2016-12-04"));
		Assert.assertEquals(list.get(0).getAdherent().getPrenom(), "zino");
		Assert.assertEquals(list.get(1).getAdherent().getPrenom(), "reb");
	}
	
	@Test
	public void testFindEmpruntForAdherent() throws ParseException{
		
		Pageable pageable = new PageRequest(0, 10);
		Page<Emprunt> page = empruntRepository.findListEmpruntByAdherentId(pageable, 10l);
		
		List<Emprunt> list = page.getContent();
		
		Assert.assertEquals(list.get(0).getMedia().getTitre(),"tt");
		Assert.assertEquals(list.get(1).getMedia().getAuteur(), "aa");
	}
	
	@Test
	public void testCreate() throws Exception{
		Media media = new Media("tititi", "auauauauteur", Media.Type.CD);
		this.mockMvc.perform(post("/api/medias").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
				.content(jsonHelper.serialize(media))).andExpect(status().isCreated());
		Adherent adherent = new Adherent("prenom", "nom", new Date(), "email");
		this.mockMvc.perform(post("/api/adherent/").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
				.content(jsonHelper.serialize(adherent))).andExpect(status().isCreated());
		
		
		Emprunt u = new Emprunt(media, adherent, new Date());
		
		this.mockMvc.perform(post("/api/emprunts").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
				.content(jsonHelper.serialize(u))).andExpect(status().isCreated());
	}
}
