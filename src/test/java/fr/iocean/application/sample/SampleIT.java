package fr.iocean.application.sample;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Date;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import fr.iocean.application.IntegrationTest;
import fr.iocean.application.model.Adherent;
import fr.iocean.application.service.AdherentService;

public class SampleIT extends IntegrationTest {
	
	@Autowired
	AdherentService adherentService;

	@Test
	public void test() {
		System.out.println("test ok");
	}
	
	@Test
	public void testCreate() throws Exception {
		
		Adherent a = new Adherent("zino", "kholladi", new Date(), "kholladi.zino@gmail.com");

		
		this.mockMvc.perform(post("/api/adherent/").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
				.content(jsonHelper.serialize(a))).andExpect(status().isCreated());
	}
	
	@Test
	public void testUpdate() throws Exception {

		Adherent a = adherentService.findById(1l);
		a.setPrenom("yohane");
		
		this.mockMvc.perform(put("/api/adherent/1").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
				.content(jsonHelper.serialize(a))).andExpect(status().isOk());
		this.mockMvc.perform(get("/api/adherent/1")).andDo(MockMvcResultHandlers.print())
				.andExpect(jsonPath("$.prenom", Is.is("yohane"))).andExpect(status().isOk());
	}
	
	
	
	 
	
}
