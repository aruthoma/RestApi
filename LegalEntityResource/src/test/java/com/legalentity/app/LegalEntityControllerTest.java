package com.legalentity.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.legalentity.app.controller.LegalEntityController;
import com.legalentity.app.model.LegalEntityBean;
import com.legalentity.app.model.LegalEntityShareHolderBean;
import com.legalentity.app.service.LegalEntityService;

@RunWith(MockitoJUnitRunner.class)
public class LegalEntityControllerTest {

	private MockMvc mockMvc;
	
	@InjectMocks
    private LegalEntityController legalEntityController;
	
	@Mock
	private LegalEntityService legalEntityService;
	
	List<LegalEntityBean> legalEntityList;

	@Test
	public void createEntitiesApiTest() {
		
		List<LegalEntityBean> legalEntityList;
		
		LegalEntityShareHolderBean entity1SH1 = new LegalEntityShareHolderBean("Entity1ShareHolder1", "US", 5);
		LegalEntityShareHolderBean entity1SH2 = new LegalEntityShareHolderBean("Entity1ShareHolder2", "US", 5);

		LegalEntityShareHolderBean entity2SH1 = new LegalEntityShareHolderBean("Entity2ShareHolder1", "UK", 5);
		LegalEntityShareHolderBean entity2SH2 = new LegalEntityShareHolderBean("Entity2ShareHolder1", "UK", 5);

		LegalEntityShareHolderBean entity3SH1 = new LegalEntityShareHolderBean("Entity3ShareHolder1", "CH", 5);
		LegalEntityShareHolderBean entity3SH2 = new LegalEntityShareHolderBean("Entity3ShareHolder1", "CH", 5);

		legalEntityList = new ArrayList<LegalEntityBean>();

		legalEntityList.add(new LegalEntityBean("Entity1", parseDate("2020-12-01"), "US", 10,
				new ArrayList<>(Arrays.asList(entity1SH1, entity1SH2))));
		legalEntityList.add(new LegalEntityBean("Entity2", parseDate("2020-11-01"), "UK", 20,
				new ArrayList<>(Arrays.asList(entity2SH1, entity2SH2))));
		legalEntityList.add(new LegalEntityBean("Entity3", parseDate("2020-10-01"), "CH", 30,
				new ArrayList<>(Arrays.asList(entity3SH1, entity3SH2))));

		when(legalEntityService.createLegalEntity()).thenReturn(legalEntityList);
		List<LegalEntityBean> legalEntitiesValues = legalEntityService.createLegalEntity();

		assertEquals("Entity1", legalEntitiesValues.get(0).getEntityName());
		assertEquals("Entity2", legalEntitiesValues.get(1).getEntityName());
		assertEquals("Entity3", legalEntitiesValues.get(2).getEntityName());

	}

	@Test
	public void deleteEntityApiTest() {
       
		LegalEntityShareHolderBean entity1SH1 = new LegalEntityShareHolderBean("Entity1ShareHolder1", "US", 5);
		LegalEntityShareHolderBean entity1SH2 = new LegalEntityShareHolderBean("Entity1ShareHolder2", "US", 5);
		legalEntityList = new ArrayList<LegalEntityBean>();

		legalEntityList.add(new LegalEntityBean("Entity1", parseDate("2020-12-01"), "US", 10,
				new ArrayList<>(Arrays.asList(entity1SH1, entity1SH2))));
	
		when(legalEntityController.deleteLegalEntityByName("Entity1")).thenReturn(legalEntityList);
		List<LegalEntityBean> legalEntitiesValues = legalEntityController.deleteLegalEntityByName("Entity1");		
		assertEquals(legalEntitiesValues.size(),1);
		
	}
	
	@Test 
	public void searchByNameTest() throws Exception {
		
		//Mock the data
		LegalEntityShareHolderBean entity1SH1 = new LegalEntityShareHolderBean("Entity1ShareHolder1", "US", 5);
		legalEntityList = new ArrayList<LegalEntityBean>();

		legalEntityList.add(new LegalEntityBean("Entity1", parseDate("2020-12-01"), "US", 10,
				new ArrayList<>(Arrays.asList(entity1SH1))));
		 
		when(legalEntityController.getLegalEntityByName("Entity1")).thenReturn(legalEntityList);
		
		mockMvc.perform(MockMvcRequestBuilders.get("getlegalentity/Entity1"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].entityName").value( "Entity1"))
		.andExpect(status().isOk());
		
		
		
		
	}

	// --Static method to convert String to Date type
	public static Date parseDate(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);

		String dateInString = date;
		try {
			Date dateToSend = formatter.parse(dateInString);
			return dateToSend;
		} catch (ParseException e) {
			return null;
		}

	};

}
