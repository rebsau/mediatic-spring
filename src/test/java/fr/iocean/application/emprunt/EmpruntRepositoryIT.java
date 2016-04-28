package fr.iocean.application.emprunt;

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

import fr.iocean.application.IntegrationTest;
import fr.iocean.application.model.Emprunt;
import fr.iocean.application.repository.EmpruntRepository;

public class EmpruntRepositoryIT extends IntegrationTest {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	EmpruntRepository empruntRepository;
	
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
}
